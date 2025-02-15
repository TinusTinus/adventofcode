package nl.mvdr.adventofcode.adventofcode2022.day12;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;

import nl.mvdr.adventofcode.point.Point;

/**
 * Height map of a hill.
 *
 * @author Martijn van de Rijdt
 */
record HeightMap(Point start, Point end, Map<Point, Character> heights) {
    
    /**
     * Parses the puzzle input into a height map.
     * 
     * @param lines the puzzle input represented as a list of lines
     * @return height map of the hill
     */
    static HeightMap parse(List<String> lines) {
        Optional<Point> start = Optional.empty();
        Optional<Point> end = Optional.empty();
        Map<Point, Character> heights = new HashMap<>();
        for (var y = 0; y != lines.size(); y++) {
            var line = lines.get(y);
            for (var x = 0; x != line.length(); x++) {
                var point = new Point(x, y);
                var c = line.charAt(x);
                char height;
                if (c == 'S') {
                    start.ifPresent(s -> { throw new IllegalArgumentException("Multiple starting points found: " + s + " and " + point); });
                    start = Optional.of(point);
                    height = 'a';
                } else if (c == 'E') {
                    end.ifPresent(e -> { throw new IllegalArgumentException("Multiple end points found: " + e + " and " + point); });
                    end = Optional.of(point);
                    height = 'z';
                } else {
                    height = c;
                }
                heights.put(point, Character.valueOf(height));
            }
        }
        return new HeightMap(start.orElseThrow(() -> new IllegalArgumentException("No start point found")), 
                end.orElseThrow(() -> new IllegalArgumentException("No end point found")),
                heights);
    }
    
    /**
     * @return length of the shortest path from start to end
     */
    int computeShortestPathLength() {
        return computeShortestPathLength(Set.of(start));
    }
    
    /**
     * @return length of the shortest path from start to end
     */
    int computeShortestPathLengthFromAnyLowestPoint() {
        Set<Point> startingPoints = heights.entrySet()
                .stream()
                .filter(entry -> entry.getValue().charValue() == 'a')
                .map(Entry::getKey)
                .collect(Collectors.toSet());
        return computeShortestPathLength(startingPoints);
    }
    
    /**
     * Computes the length of the shortest path, starting from any of the given starting points.
     * 
     * @param startPoints the possible starting points
     * @return length of the shortest path from the given starting point to the end
     */
    private int computeShortestPathLength(Set<Point> startPoints) {
        // Create a directed graph
        Graph<Point, DefaultEdge> graph = new SimpleDirectedGraph<>(DefaultEdge.class);
        // Each square is a vertex in the graph
        heights.keySet().forEach(graph::addVertex);
        // Add edges
        heights.entrySet().forEach(entry ->
            entry.getKey().neighbours()
                    .stream()
                    .filter(heights::containsKey)
                    // Only include an edge if the neighbours' height is at most one higher than this square's height
                    .filter(neighbour -> heights.get(neighbour).charValue() <= entry.getValue().charValue() + 1)
                    .forEach(neighbour -> graph.addEdge(entry.getKey(), neighbour)));
        
        ShortestPathAlgorithm<Point, DefaultEdge> algorithm = new DijkstraShortestPath<>(graph);
        return startPoints.stream()
                .map(startingPoint -> algorithm.getPath(startingPoint, end))
                // Exclude starting points for which there is no path to the end
                .filter(Objects::nonNull)
                .mapToInt(GraphPath::getLength)
                .min()
                .orElseThrow();
        
        // Note: the above could probably be made more efficient by inverting all edges, and then using DijkstraShortestPath.getPaths(end).
        // However, the above solution is efficient enough: it finds the answer on my machine within a second.
    }
}
