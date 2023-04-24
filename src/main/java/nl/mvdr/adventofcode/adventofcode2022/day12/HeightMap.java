package nl.mvdr.adventofcode.adventofcode2022.day12;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
        Graph<Point, DefaultEdge> graph = new SimpleDirectedGraph<>(DefaultEdge.class);
        heights.keySet().forEach(graph::addVertex);
        heights.keySet().forEach(point -> {
            var height = heights.get(point).charValue();
            point.neighbours()
                    .stream()
                    .filter(heights::containsKey)
                    .filter(neighbour -> heights.get(neighbour).charValue() <= height + 1)
                    .forEach(neighbour -> graph.addEdge(point, neighbour));
        });
        
        ShortestPathAlgorithm<Point, DefaultEdge> algorithm = new DijkstraShortestPath<>(graph);
        GraphPath<Point, DefaultEdge> path = algorithm.getPath(start, end);
        return path.getLength();
    }
}
