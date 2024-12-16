package nl.mvdr.adventofcode.adventofcode2024.day16;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm;
import org.jgrapht.alg.shortestpath.AllDirectedPaths;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultDirectedWeightedGraph;
import org.jgrapht.graph.DefaultEdge;

import nl.mvdr.adventofcode.point.Direction;
import nl.mvdr.adventofcode.point.Orientation;
import nl.mvdr.adventofcode.point.Point;

record Maze(Graph<PointAndDirection, DefaultEdge> graph, PointAndDirection startVertex, PointAndDirection endVertex) {
    
    static Maze parse(List<String> lines) {
        Set<Point> startingPoints = new HashSet<>();
        Set<Point> endPoints = new HashSet<>();
        Set<Point> tiles = new HashSet<>();
        
        Point.parse2DMap(lines, (point, c) -> {
            if (c == 'S') {
                startingPoints.add(point);
                tiles.add(point);
            } else if (c == 'E') {
                endPoints.add(point);
                tiles.add(point);
            } else if (c == '.') {
                tiles.add(point);
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
        
        PointAndDirection startVertex = new PointAndDirection(startingPoints.iterator().next(), Direction.RIGHT);
        
        Point end = endPoints.iterator().next();
        
        
        Graph<PointAndDirection, DefaultEdge> graph = new DefaultDirectedWeightedGraph<>(DefaultEdge.class);
        
        tiles.stream()
                .flatMap(tile -> Stream.of(Direction.values())
                        .filter(direction -> direction.getOrientation() != Orientation.DIAGONAL)
                        .map(direction -> new PointAndDirection(tile, direction)))
                .forEach(graph::addVertex);

        graph.vertexSet()
                .stream()
                .filter(vertex -> graph.containsVertex(vertex.move()))
                .map(vertex -> graph.addEdge(vertex, vertex.move()))
                .forEach(edge -> graph.setEdgeWeight(edge, 1));
        graph.vertexSet()
                .stream()
                .map(vertex -> graph.addEdge(vertex, vertex.turnClockwise()))
                .forEach(edge -> graph.setEdgeWeight(edge, 1_000));
        graph.vertexSet()
                .stream()
                .map(vertex -> graph.addEdge(vertex, vertex.turnCounterClockwise()))
                .forEach(edge -> graph.setEdgeWeight(edge, 1_000));
        
        // We do not care which direction we are facing for the end!
        Stream.of(Direction.values())
                .filter(direction -> direction.getOrientation() != Orientation.DIAGONAL)
                .map(direction -> new PointAndDirection(end, direction))
                .peek(vertex -> graph.setEdgeWeight(vertex, vertex.turnClockwise(), 0))
                .forEach(vertex -> graph.setEdgeWeight(vertex, vertex.turnCounterClockwise(), 0));
        PointAndDirection endVertex = new PointAndDirection(end, Direction.RIGHT);
        
        return new Maze(graph, startVertex, endVertex);
    }
    
    int computeLowestScore() {
        var path = computeShortestPath();
        return (int) path.getWeight();
    }

    private GraphPath<PointAndDirection, DefaultEdge> computeShortestPath() {
        ShortestPathAlgorithm<PointAndDirection, DefaultEdge> algorithm = new DijkstraShortestPath<>(graph);
        return algorithm.getPath(startVertex, endVertex);
    }
    
    long countBestPathTiles() {
        var shortestPath = computeShortestPath();
        
        AllDirectedPaths<PointAndDirection, DefaultEdge> algorithm = new AllDirectedPaths<>(graph);
        
        var paths = algorithm.getAllPaths(startVertex, endVertex, true, Integer.valueOf(shortestPath.getLength()));
                
        return paths.stream()
                .filter(path -> path.getWeight() == shortestPath.getWeight())
                .flatMap(path -> path.getVertexList().stream())
                .map(PointAndDirection::point)
                .distinct()
                .count();
    }
}
