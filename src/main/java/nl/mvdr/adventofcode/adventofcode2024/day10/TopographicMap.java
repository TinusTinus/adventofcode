package nl.mvdr.adventofcode.adventofcode2024.day10;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.jgrapht.Graph;
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DirectedAcyclicGraph;

import nl.mvdr.adventofcode.point.Point;

record TopographicMap(Map<Point, Integer> heights) {

    private static int parseHeight(char c) {
        return switch (c) {
            case '.' -> Integer.MAX_VALUE; // Impassable tiles. Should only occur in examples, not in the actual puzzle input.
            default -> Character.getNumericValue(c);
        };
    }
    
    TopographicMap(List<String> lines) {
        this(Point.parse2DMap(lines, TopographicMap::parseHeight));
    }
    
    private Set<Point> getTrailheads() {
        return getPointsAt(0);
    }
    
    private Set<Point> getMountaintops() {
        return getPointsAt(9);
    }
    
    private Set<Point> getPointsAt(int height) {
        return heights.entrySet()
                .stream()
                .filter(entry -> entry.getValue().intValue() == height)
                .map(Entry::getKey)
                .collect(Collectors.toSet());
    }

    private Graph<Point, DefaultEdge> createGraph() {
        Graph<Point, DefaultEdge> result = new DirectedAcyclicGraph<>(DefaultEdge.class);
        
        heights.keySet().stream().forEach(result::addVertex);
        
        heights.keySet()
                .stream()
                .forEach(point -> point.neighbours()
                        .stream()
                        .filter(neighbour -> heights.containsKey(neighbour) && heights.get(neighbour).intValue() == heights.get(point).intValue() + 1)
                        .forEach(neighbour -> result.addEdge(point, neighbour)));
        
        return result;
    }
    
    long calculateTrailheadScores() {
        var graph = createGraph();
        
        ShortestPathAlgorithm<Point, DefaultEdge> algorithm = new DijkstraShortestPath<>(graph);

        var mountaintops = getMountaintops();
        
        return getTrailheads().stream()
                .map(algorithm::getPaths)
                .flatMap(paths -> mountaintops.stream().map(mountainhead -> paths.getPath(mountainhead)))
                .filter(Objects::nonNull)
                .count();
    }
    
}
