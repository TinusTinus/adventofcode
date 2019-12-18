package nl.mvdr.adventofcode.adventofcode2019.day18;

import java.util.Collections;
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

import nl.mvdr.adventofcode.point.Point;

/**
 * Representation of the state of an underground vault.
 *
 * @author Martijn van de Rijdt
 */
class Vault {

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
                0);
    }
    
    /**
     * Constructor.
     * 
     * @param startingPoint starting point
     * @param openPassages open passages
     * @param closedDoors closed doors
     * @param keys keys
     * @param steps steps taken so far
     */
    private Vault(Point startingPoint, Set<Point> openPassages, Map<Character, Point> closedDoors,
            Map<Character, Point> keys, int steps) {
        super();
        this.startingPoint = startingPoint;
        this.openPassages = openPassages;
        this.closedDoors = closedDoors;
        this.keys = keys;
        this.steps = steps;
    }

    int shortestPathToPickUpAllKeys() {
        return pickUpKeys()
                .mapToInt(vault -> vault.steps)
                .min()
                .orElse(0);
    }
    
    /** @return possible states of the vault after picking up every key */
    private Stream<Vault> pickUpKeys() {
        Stream<Vault> result;
        
        if (keys.isEmpty()) {
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
            if (path != null) {
                Point newstartingPoint = key.getValue();
                
                // Found a path to the key!
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
                
                result.add(new Vault(newstartingPoint, newOpenPassages, newClosedDoors, newKeys, newSteps));
            }
        }
        
        return result.stream();
    }

    /**
     * @return
     */
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
        
        ShortestPathAlgorithm<Point, DefaultEdge> shortestPathAlgorithm = new DijkstraShortestPath<>(graph);
        return shortestPathAlgorithm;
    }
}
