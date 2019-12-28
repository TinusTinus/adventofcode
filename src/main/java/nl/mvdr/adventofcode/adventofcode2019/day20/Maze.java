package nl.mvdr.adventofcode.adventofcode2019.day20;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
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
 * Representation of the maze.
 *
 * @author Martijn van de Rijdt
 */
class Maze {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(Maze.class);

    private final Point start;
    private final Point finish;
    private final Set<Point> openPassages;
    private final Set<Portal> portals;
    
    /**
     * Parses the given input into a maze.
     * 
     * @param linesStream contents of the puzzle input
     * @return maze
     */
    static Maze parse(Stream<String> linesStream) {
        List<String> lines = linesStream.filter(Predicate.not(String::isBlank))
                .collect(Collectors.toList());
        
        // Build a map containing all open passages (marked as '.' on the map) as its keys,
        // and their labels as its values.
        Set<Point> openPassages = new HashSet<>();
        
        Map<String, Point> outerLabels = new HashMap<>();
        Map<String, Point> innerLabels = new HashMap<>();
        
        for (int y = 0; y != lines.size(); y++) {
            String line = lines.get(y);
            for (int x = 0; x != line.length(); x++) {
                if (line.charAt(x) == '.') {
                    Point point = new Point(x, y);
                    
                    openPassages.add(point);
                    
                    Optional<String> label;
                    boolean outer;
                    if (Character.isUpperCase(line.charAt(x - 2)) && Character.isUpperCase(line.charAt(x - 1))) {
                        // there is a label to the left of this passage
                        label = Optional.of(line.substring(x - 2, x));
                        outer = x - 2 == 0;
                    } else if (Character.isUpperCase(line.charAt(x + 1)) && Character.isUpperCase(line.charAt(x + 2))) {
                        // there is a label to the right of this passage
                        label = Optional.of(line.substring(x + 1, x + 3));
                        outer = x + 3 == line.length();
                    } else if (Character.isUpperCase(lines.get(y - 2).charAt(x)) && Character.isUpperCase(lines.get(y - 1).charAt(x))) {
                        // there is a label above this passage
                        label = Optional.of("" + lines.get(y - 2).charAt(x) + lines.get(y - 1).charAt(x));
                        outer = y - 2 == 0;
                    } else if (Character.isUpperCase(lines.get(y + 1).charAt(x)) && Character.isUpperCase(lines.get(y + 2).charAt(x))) {
                        // there is a label below this passage
                        label = Optional.of("" + lines.get(y + 1).charAt(x) + lines.get(y + 2).charAt(x));
                        outer = y + 3 == lines.size();
                    } else {
                        // no label found
                        label = Optional.empty();
                        outer = false;
                    }
                    Map<String, Point> labels;
                    if (outer) {
                        labels = outerLabels;
                    } else {
                        labels = innerLabels;
                    }
                    label.ifPresent(name -> labels.put(name, point));
                }
            }
        }
        
        LOGGER.debug("Open passages found: {}", openPassages);
        LOGGER.debug("Outer labels: {}, inner labels: {}", outerLabels, innerLabels);
        
        Point start = outerLabels.get("AA");
        Point finish = outerLabels.get("ZZ");
        
        Set<Portal> portals = innerLabels.entrySet()
                .stream()
                .map(entry -> new Portal(entry.getKey(), outerLabels.get(entry.getKey()), entry.getValue()))
                .collect(Collectors.toSet());
        
        return new Maze(start, finish, openPassages, portals);
    }

    /**
     * Constructor.
     * 
     * @param start starting point of the maze
     * @param finish finish point of the maze
     * @param openPassages open passages in the maze
     * @param portals teleportation points in the maze
     */
    private Maze(Point start, Point finish, Set<Point> openPassages, Set<Portal> portals) {
        super();
        this.start = start;
        this.finish = finish;
        this.openPassages = openPassages;
        this.portals = portals;
    }
    
    /** @return length of a shortest path from start to finish */
    int shortestPath() {
        Graph<Point, DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);
        
        openPassages.forEach(graph::addVertex);
        
        openPassages.stream()
                .filter(openPassage -> openPassages.contains(openPassage.rightNeighbour()))
                .forEach(openPassage -> graph.addEdge(openPassage, openPassage.rightNeighbour()));
        openPassages.stream()
                .filter(openPassage -> openPassages.contains(openPassage.belowNeighbour()))
                .forEach(openPassage -> graph.addEdge(openPassage, openPassage.belowNeighbour()));
        portals.forEach(portal -> graph.addEdge(portal.getOuterPoint(), portal.getInnerPoint()));
        
        return shortestPath(graph, start, finish);
    }
    
    /** 
     * Computes the length of a shortest path from start to finish, using the rules for recursive space.
     * 
     * @param maxLayers maximum depth of layers to inspect
     * @return length of a shortest path from start to finish in recursive space
     */
    int shortestPathInRecursiveSpace(int maxLayers) {
        Graph<LayeredPoint, DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);
        
        IntStream.range(0, maxLayers).forEach(layer -> {
            openPassages.stream()
                    .map(openPassage -> new LayeredPoint(openPassage, layer))
                    .forEach(graph::addVertex);
            
            openPassages.stream()
                    .filter(openPassage -> openPassages.contains(openPassage.rightNeighbour()))
                    .forEach(openPassage -> graph.addEdge(new LayeredPoint(openPassage, layer), new LayeredPoint(openPassage.rightNeighbour(), layer)));
            openPassages.stream()
                    .filter(openPassage -> openPassages.contains(openPassage.belowNeighbour()))
                    .forEach(openPassage -> graph.addEdge(new LayeredPoint(openPassage, layer), new LayeredPoint(openPassage.belowNeighbour(), layer)));
            if (0 < layer) {
                portals.forEach(portal -> graph.addEdge(new LayeredPoint(portal.getOuterPoint(), layer), new LayeredPoint(portal.getInnerPoint(), layer - 1)));
            }
        });
        
        return shortestPath(graph, new LayeredPoint(start, 0), new LayeredPoint(finish, 0));
    }

    /**
     * Computes the length of a shortest path from start to finish.
     * 
     * @param <T> type of vertices in the graph
     * @param graph graph based on this maze
     * @param startVertex representation of the starting point
     * @param finishVertex representation of the finish line
     * @return length of a shortest path from start to finish
     */
    private <T> int shortestPath(Graph<T, DefaultEdge> graph, T startVertex, T finishVertex) {
        ShortestPathAlgorithm<T, DefaultEdge> shortestPathAlgorithm = new DijkstraShortestPath<>(graph);
        GraphPath<T, DefaultEdge> path = shortestPathAlgorithm.getPath(startVertex, finishVertex);
        if (path == null) {
            throw new NoSuchElementException("No shortest path found.");
        }
        return path.getLength();
    }

    @Override
    public String toString() {
        return "Maze [start=" + start + ", finish=" + finish + ", portals=" + portals + "]";
    }
}
