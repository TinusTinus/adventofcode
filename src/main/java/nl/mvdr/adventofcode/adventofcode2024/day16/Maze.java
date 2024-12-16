package nl.mvdr.adventofcode.adventofcode2024.day16;

import nl.mvdr.adventofcode.point.Direction;
import nl.mvdr.adventofcode.point.Orientation;
import nl.mvdr.adventofcode.point.Point;
import org.jgrapht.Graph;
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultDirectedWeightedGraph;
import org.jgrapht.graph.DefaultEdge;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

record Maze(PointAndDirection start, Point end, Set<Point> walkways) {
    
    static Maze parse(List<String> lines) {
        Set<Point> startingPoints = new HashSet<>();
        Set<Point> endPoints = new HashSet<>();
        Set<Point> walkways = new HashSet<>();
        
        Point.parse2DMap(lines, (point, c) -> {
            if (c == 'S') {
                startingPoints.add(point);
                walkways.add(point);
            } else if (c == 'E') {
                endPoints.add(point);
                walkways.add(point);
            } else if (c == '.') {
                walkways.add(point);
            } else if (c != '#') {
                throw new IllegalArgumentException("Unable to process character: " + c);
            }
        });
        
        if (startingPoints.size() != 1) {
            throw new IllegalArgumentException("Expected 1 starting point, found: " + startingPoints);
        }
        if (endPoints.size() != 1) {
            throw new IllegalArgumentException("Expected 1 end point, found: " + endPoints);
        }
        
        return new Maze(
                new PointAndDirection(startingPoints.iterator().next(), Direction.RIGHT),
                endPoints.iterator().next(),
                walkways);
    }
    
    private Graph<PointAndDirection, DefaultEdge> createGraph() {
        Graph<PointAndDirection, DefaultEdge> result = new DefaultDirectedWeightedGraph<>(DefaultEdge.class);
        
        walkways.stream()
                .flatMap(walkway -> Stream.of(Direction.values())
                        .filter(direction -> direction.getOrientation() != Orientation.DIAGONAL)
                        .map(direction -> new PointAndDirection(walkway, direction)))
                .forEach(result::addVertex);

        result.vertexSet()
                .stream()
                .filter(vertex -> result.containsVertex(vertex.move()))
                .map(vertex -> result.addEdge(vertex, vertex.move()))
                .forEach(edge -> result.setEdgeWeight(edge, 1));
        result.vertexSet()
                .stream()
                .map(vertex -> result.addEdge(vertex, vertex.turnClockwise()))
                .forEach(edge -> result.setEdgeWeight(edge, 1_000));
        result.vertexSet()
                .stream()
                .map(vertex -> result.addEdge(vertex, vertex.turnCounterClockwise()))
                .forEach(edge -> result.setEdgeWeight(edge, 1_000));
        
        return result;
    }
    
    int computeLowestScore() {
        var graph = createGraph();
        
        ShortestPathAlgorithm<PointAndDirection, DefaultEdge> algorithm = new DijkstraShortestPath<PointAndDirection, DefaultEdge>(graph);
        
        var paths = algorithm.getPaths(start);
        
        return Stream.of(Direction.values())
                .map(direction -> new PointAndDirection(end, direction))
                .mapToDouble(paths::getWeight)
                .mapToInt(d -> (int) d)
                .min()
                .orElseThrow();
    }
}
