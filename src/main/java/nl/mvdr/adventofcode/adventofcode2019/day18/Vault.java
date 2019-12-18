package nl.mvdr.adventofcode.adventofcode2019.day18;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.point.Point;

/**
 * Representation of the state of an underground vault.
 *
 * @author Martijn van de Rijdt
 */
class Vault {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(Vault.class);

    /** Current location. */
    private final Point startingPoint;
    
    /**
     * Open passages, which can be freely traveled through.
     * 
     * These include the starting point and key locations,
     * but not walls and closed doors.
     */
    private final Set<Point> openPassages;

    /**
     * Doors for which the key has not yet been obtained.
     * 
     * Keys in this map are uppercase characters, corresponding to the lowercase key.
     */
    private final Map<Character, Point> closedDoors;
    
    /**
     * Locations of keys, which have not yet been obtained.
     * 
     * Keys in this map are lowercase characters, corresponding to the uppercase door.
     */
    private final Map<Character, Point> keys;
    
    /** The number of steps taken so far from the entrance to get to {@link #startingPoint}. */
    private final int steps;
    
    /** Keys that have been picked up so far. */
    private final List<Character> keysPickedUp;

    /**
     * Parses the given input into a {@code Vault}.
     * 
     * @param linesStream contents of the input
     * @return vault
     */
    static Vault parse(Stream<String> linesStream) {
        Optional<Point> startingPoint = Optional.empty();
        Set<Point> openPassages = new HashSet<>();
        Map<Character, Point> closedDoors = new HashMap<>();
        Map<Character, Point> keys = new HashMap<>();
        
        List<String> lines = linesStream.collect(Collectors.toList());
        for (int y = 0; y != lines.size(); y++) {
            String line = lines.get(y);
            for (int x = 0; x != line.length(); x++) {
                Point point = new Point(x, y);
                char c = line.charAt(x);
                if (c == '@' && startingPoint.isPresent()) {
                    throw new IllegalArgumentException("Multiple starting points found.");
                } else if (c == '@') {
                    startingPoint = Optional.of(point);
                    openPassages.add(point);
                } else if (c == '.') {
                    openPassages.add(point);
                } else if (Character.isLowerCase(c)) {
                    keys.put(Character.valueOf(c), point);
                    openPassages.add(point);
                } else if (Character.isUpperCase(c)) {
                    closedDoors.put(Character.valueOf(c), point);
                } else if (c != '#') {
                    throw new IllegalArgumentException("Unexpected input: " + c);
                }
            }
        }
        
        return new Vault(startingPoint.orElseThrow(),
                Collections.unmodifiableSet(openPassages),
                Collections.unmodifiableMap(closedDoors),
                Collections.unmodifiableMap(keys),
                0,
                List.of());
    }
    
    /**
     * Constructor.
     * 
     * @param startingPoint starting point
     * @param openPassages open passages
     * @param closedDoors closed doors
     * @param keys keys
     * @param steps steps taken so far
     * @param keysPickedUp keys picked up so far
     */
    private Vault(Point startingPoint, Set<Point> openPassages, Map<Character, Point> closedDoors,
            Map<Character, Point> keys, int steps, List<Character> keysPickedUp) {
        super();
        this.startingPoint = startingPoint;
        this.openPassages = openPassages;
        this.closedDoors = closedDoors;
        this.keys = keys;
        this.steps = steps;
        this.keysPickedUp = keysPickedUp;
    }

    /** @return length of a shortest path to pick up all of the keys in this vault */
    // TODO This solution is technically correct, but very slow due to the recursion.
    // Consider computing shortest paths between each pair of keys (and keeping track of which doors are in the way).
    // (Which should only work if waiting for the doors along the shortest path have opened is better than a slightly longer path with no / other doors...) 
    int shortestPathToPickUpAllKeys() {
        Vault vault = pickUpKeys()
                .min(Comparator.comparingInt(v -> v.steps))
                .orElseThrow();
        
        LOGGER.info("Shortest path: {}", vault.keysPickedUp);
        
        return vault.steps;
    }
    
    /** @return possible states of the vault after picking up every key */
    private Stream<Vault> pickUpKeys() {
        Stream<Vault> result;
        
        if (keys.isEmpty()) {
            LOGGER.debug("{}: all keys have been picked up", keysPickedUp);
            result = Stream.of(this);
        } else {
            // Recursively call this method after picking up a single key
            result = pickUpKey()
                    .parallel()
                    .flatMap(Vault::pickUpKeys);
        }
        
        return result;
    }
    
    /** @return possible states of the vault after picking up a single key */
    private Stream<Vault> pickUpKey() {
        Set<Vault> result = new HashSet<>();
        
        ShortestPathAlgorithm<Point, DefaultEdge> shortestPathAlgorithm = createShortestPathAlgorithm();
        
        for (Entry<Character, Point> key : keys.entrySet()) {
            GraphPath<Point, DefaultEdge> path = shortestPathAlgorithm.getPath(startingPoint, key.getValue());
            if (path == null) {
                LOGGER.debug("{}: unable to reach key {}", keysPickedUp, key.getKey());
            } else {
                Point newstartingPoint = key.getValue();
                
                char doorName = Character.toUpperCase(key.getKey().charValue());
                
                Map<Character, Point> newClosedDoors = new HashMap<>(closedDoors);
                Point openedDoor = newClosedDoors.remove(Character.valueOf(doorName));

                Set<Point> newOpenPassages;
                if (openedDoor == null) {
                    newOpenPassages = openPassages;
                } else {
                    newOpenPassages = new HashSet<>(openPassages);
                    newOpenPassages.add(openedDoor);
                }
                
                Map<Character, Point> newKeys = new HashMap<>(keys);
                newKeys.remove(key.getKey());
                
                int newSteps = steps + path.getLength();
                
                List<Character> newKeysPickedUp = new ArrayList<>(keysPickedUp);
                newKeysPickedUp.add(key.getKey());
                
                LOGGER.debug("{}: picking up {}, total steps: {}", keysPickedUp, key.getKey(), Integer.valueOf(newSteps));
                
                result.add(new Vault(newstartingPoint, newOpenPassages, newClosedDoors, newKeys, newSteps, newKeysPickedUp));
                
            }
        }
        
        return result.stream();
    }

    private ShortestPathAlgorithm<Point, DefaultEdge> createShortestPathAlgorithm() {
        Graph<Point, DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);
        openPassages.forEach(graph::addVertex);
        
        for (Point openPassage : openPassages) {
            if (openPassages.contains(openPassage.eastNeighbour())) {
                graph.addEdge(openPassage, openPassage.eastNeighbour());
            }
            if (openPassages.contains(openPassage.southNeighbour())) {
                graph.addEdge(openPassage, openPassage.southNeighbour());
            }
        }
        
        return new DijkstraShortestPath<>(graph);
    }
}
