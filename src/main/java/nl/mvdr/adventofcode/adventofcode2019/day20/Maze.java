package nl.mvdr.adventofcode.adventofcode2019.day20;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.jgrapht.Graph;
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
     * @param lines contents of the puzzle input
     * @return maze
     */
    static Maze parse(List<String> lines) {
        // Build a map containing all open passages (marked as '.' on the map) as its keys,
        // and their labels as its values.
        Set<Point> openPassages = new HashSet<>();
        
        Map<String, Set<Point>> labels = new HashMap<>();
        
        for (int y = 0; y != lines.size(); y++) {
            String line = lines.get(y);
            for (int x = 0; x != line.length(); x++) {
                if (line.charAt(x) == '.') {
                    Point point = new Point(x, y);
                    
                    openPassages.add(point);
                    
                    Optional<String> label;
                    if (Character.isUpperCase(line.charAt(x - 2)) && Character.isUpperCase(line.charAt(x - 1))) {
                        // there is a label to the left of this passage
                        label = Optional.of(line.substring(x - 2, x));
                    } else if (Character.isUpperCase(line.charAt(x + 1)) && Character.isUpperCase(line.charAt(x + 2))) {
                        // there is a label to the right of this passage
                        label = Optional.of(line.substring(x + 1, x + 3));
                    } else if (Character.isUpperCase(lines.get(y - 2).charAt(x)) && Character.isUpperCase(lines.get(y - 1).charAt(x))) {
                        // there is a label above this passage
                        label = Optional.of("" + lines.get(y - 2).charAt(x) + lines.get(y - 1).charAt(x));
                    } else if (Character.isUpperCase(lines.get(y + 1).charAt(x)) && Character.isUpperCase(lines.get(y + 2).charAt(x))) {
                        // there is a label below this passage
                        label = Optional.of("" + lines.get(y + 1).charAt(x) + lines.get(y + 2).charAt(x));
                    } else {
                        // no label found
                        label = Optional.empty();
                    }
                    label.ifPresent(name -> labels.computeIfAbsent(name, k -> new HashSet<>()).add(point));
                    
                }
            }
        }
        
        LOGGER.debug("Open passages found: {}", openPassages);
        LOGGER.debug("Labels: {}", labels);
        
        Point start = labels.remove("AA").iterator().next();
        Point finish = labels.remove("ZZ").iterator().next();
        
        Set<Portal> portals = labels.values()
                .stream()
                .map(Set::iterator)
                .map(iterator -> new Portal(iterator.next(), iterator.next()))
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
    
    /** @return length of the shortest path from start to finish */
    int shortestPath() {
        Graph<Point, DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);
        
        openPassages.forEach(graph::addVertex);
        
        openPassages.stream()
                .filter(openPassage -> openPassages.contains(openPassage.rightNeighbour()))
                .forEach(openPassage -> graph.addEdge(openPassage, openPassage.rightNeighbour()));
        openPassages.stream()
                .filter(openPassage -> openPassages.contains(openPassage.belowNeighbour()))
                .forEach(openPassage -> graph.addEdge(openPassage, openPassage.belowNeighbour()));
        portals.forEach(portal -> graph.addEdge(portal.getPoint0(), portal.getPoint1()));
        
        ShortestPathAlgorithm<Point, DefaultEdge> shortestPathAlgorithm = new DijkstraShortestPath<>(graph);
        return shortestPathAlgorithm.getPath(start, finish).getLength();
    }
}
