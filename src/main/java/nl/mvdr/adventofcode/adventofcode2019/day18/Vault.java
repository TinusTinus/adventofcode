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
     * Locations of keys, which have not yet been obtained.
     * 
     * Keys in this map are lowercase characters, corresponding to the uppercase door.
     */
    private final Map<Character, Point> keys;
    
    /** The number of steps taken so far from the entrance to get to {@link #startingPoint}. */
    private final int steps;
    
    /** Keys that have been picked up so far. */
    private final List<Character> keysPickedUp;
    
    /** Precomputed paths between points of interest. */
    private final Set<Path> precomputedPaths;

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
                if (c == '@') {
                    startingPoint = Optional.of(point);
                } else if (c == '.') {
                    openPassages.add(point);
                } else if (Character.isLowerCase(c)) {
                    keys.put(Character.valueOf(c), point);
                } else if (Character.isUpperCase(c)) {
                    closedDoors.put(Character.valueOf(c), point);
                } else if (c != '#') {
                    throw new IllegalArgumentException("Unexpected input: " + c);
                }
            }
        }
        
        openPassages = Collections.unmodifiableSet(openPassages);
        closedDoors = Collections.unmodifiableMap(closedDoors);
        keys = Collections.unmodifiableMap(keys);
        
        Set<Path> precomputedPaths = precomputePaths(startingPoint.orElseThrow(), openPassages, closedDoors, keys);
        
        return new Vault(startingPoint.orElseThrow(), keys, 0, List.of(), precomputedPaths);
    }
    
    private static Set<Path> precomputePaths(Point startingPoint, Set<Point> openPassages, Map<Character, Point> closedDoors,
            Map<Character, Point> keys) {
        
        Set<Point> pointsOfInterest = new HashSet<>();
        pointsOfInterest.add(startingPoint);
        pointsOfInterest.addAll(keys.values());
        
        Set<Point> points = new HashSet<>();
        points.addAll(openPassages);
        points.addAll(pointsOfInterest);
        points.addAll(closedDoors.values());
        
        Graph<Point, DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);
        points.forEach(graph::addVertex);
        
        for (Point point : points) {
            if (points.contains(point.eastNeighbour())) {
                graph.addEdge(point, point.eastNeighbour());
            }
            if (points.contains(point.southNeighbour())) {
                graph.addEdge(point, point.southNeighbour());
            }
        }
        
        ShortestPathAlgorithm<Point, DefaultEdge> shortestPathAlgorithm = new DijkstraShortestPath<>(graph);
        
        Set<Path> result = new HashSet<>();
        for (Point source : pointsOfInterest) {
            for (Point target : pointsOfInterest) {
                if (!source.equals(target)) {
                    GraphPath<Point, DefaultEdge> graphPath = shortestPathAlgorithm.getPath(source, target);
                    
                    Set<Character> requiredKeys = closedDoors.entrySet().stream()
                            .filter(entry -> graphPath.getVertexList().contains(entry.getValue()))
                            .map(Entry::getKey)
                            .map(c -> Character.valueOf(Character.toLowerCase(c.charValue())))
                            .collect(Collectors.toSet());
                    
                    Set<Character> keysOnTheWay = keys.entrySet().stream()
                            .filter(entry -> graphPath.getVertexList().subList(1, graphPath.getVertexList().size() - 1).contains(entry.getValue()))
                            .map(Entry::getKey)
                            .collect(Collectors.toSet());
                    
                    result.add(new Path(source, target, graphPath.getLength(), requiredKeys, keysOnTheWay));
                }
            }
        }
        return Collections.unmodifiableSet(result);
    }
    
    /**
     * Constructor.
     * 
     * @param startingPoint starting point
     * @param keys keys
     * @param steps steps taken so far
     * @param keysPickedUp keys picked up so far
     * @param precomputedPaths precomputed paths between keys and other points of interest
     */
    private Vault(Point startingPoint, Map<Character, Point> keys, int steps, List<Character> keysPickedUp,
            Set<Path> precomputedPaths) {
        super();
        this.startingPoint = startingPoint;
        this.keys = keys;
        this.steps = steps;
        this.keysPickedUp = keysPickedUp;
        this.precomputedPaths = precomputedPaths;
    }

    /** @return length of a shortest path to pick up all of the keys in this vault */
    int shortestPathToPickUpAllKeys() {
        Vault vault = pickUpKeys()
                .min(Comparator.comparingInt(v -> v.steps))
                .orElseThrow();
        
        LOGGER.debug("Shortest path: {}", vault.keysPickedUp);
        
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
                    .flatMap(Vault::pickUpKeys);
        }
        
        return result;
    }
    
    /** @return possible states of the vault after picking up a single key */
    private Stream<Vault> pickUpKey() {
        return keys.entrySet().parallelStream()
            .flatMap(key -> findPrecomputedPathTo(key.getValue()).map(path -> {
                Point newstartingPoint = key.getValue();
                
                Map<Character, Point> newKeys = new HashMap<>(keys);
                newKeys.remove(key.getKey());
                
                int newSteps = steps + path.getLength();
                
                List<Character> newKeysPickedUp = new ArrayList<>(keysPickedUp);
                newKeysPickedUp.add(key.getKey());
                
                LOGGER.debug("{}: picking up {}, total steps: {}", keysPickedUp, key.getKey(), Integer.valueOf(newSteps));
                
                return new Vault(newstartingPoint, newKeys, newSteps, newKeysPickedUp, precomputedPaths);
            }).stream());
    }
    
    private Optional<Path> findPrecomputedPathTo(Point target) {
        return precomputedPaths.stream()
                .filter(path -> path.getStart().equals(startingPoint) && path.getFinish().equals(target))
                .findFirst()
                // check that we have the key to every door along the way
                .filter(path -> path.getRequiredKeys().stream().allMatch(keysPickedUp::contains))
                // ignore paths where we step over a key without picking it up
                .filter(path -> path.getKeysOnTheWay().stream().allMatch(keysPickedUp::contains));
    }
}
