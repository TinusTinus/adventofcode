package nl.mvdr.adventofcode.adventofcode2024.day16;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import org.jgrapht.Graph;
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultDirectedWeightedGraph;
import org.jgrapht.graph.DefaultEdge;

import nl.mvdr.adventofcode.point.Direction;
import nl.mvdr.adventofcode.point.Orientation;
import nl.mvdr.adventofcode.point.Point;

/// Representation of the maze as a graph.
/// Vertices are positions in the maze, consisting of a location and direction.
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
        
        Point start = startingPoints.iterator().next();
        Point end = endPoints.iterator().next();
        
        return createMaze(tiles, start, end);
    }

    private static Maze createMaze(Set<Point> tiles, Point start, Point end) {
        Graph<PointAndDirection, DefaultEdge> graph = new DefaultDirectedWeightedGraph<>(DefaultEdge.class);
        
        tiles.stream()
                .flatMap(tile -> Stream.of(Direction.values())
                        .filter(direction -> direction.getOrientation() != Orientation.DIAGONAL)
                        .map(direction -> new PointAndDirection(tile, direction)))
                .forEach(graph::addVertex);

        // Traveling a single step has weight 1.
        graph.vertexSet()
                .stream()
                .filter(vertex -> graph.containsVertex(vertex.move()))
                .map(vertex -> graph.addEdge(vertex, vertex.move()))
                .forEach(edge -> graph.setEdgeWeight(edge, 1));
        
        // Turning has weight 1000.
        graph.vertexSet()
                .stream()
                .map(vertex -> graph.addEdge(vertex, vertex.turnClockwise()))
                .forEach(edge -> graph.setEdgeWeight(edge, 1_000));
        graph.vertexSet()
                .stream()
                .map(vertex -> graph.addEdge(vertex, vertex.turnCounterClockwise()))
                .forEach(edge -> graph.setEdgeWeight(edge, 1_000));
        
        PointAndDirection startVertex = new PointAndDirection(start, Direction.RIGHT);
        
        // We do not care which direction we are facing at the end.
        // Make turning at the end free (weight 0).
        Stream.of(Direction.values())
                .filter(direction -> direction.getOrientation() != Orientation.DIAGONAL)
                .map(direction -> new PointAndDirection(end, direction))
                .peek(vertex -> graph.setEdgeWeight(vertex, vertex.turnClockwise(), 0))
                .forEach(vertex -> graph.setEdgeWeight(vertex, vertex.turnCounterClockwise(), 0));
        // Now we can pick any direction and just consider that the end vertex.
        PointAndDirection endVertex = new PointAndDirection(end, Direction.RIGHT);
        
        return new Maze(graph, startVertex, endVertex);
    }
    
    int computeLowestScore() {
        ShortestPathAlgorithm<PointAndDirection, DefaultEdge> algorithm = new DijkstraShortestPath<>(graph);
        return (int) algorithm.getPathWeight(startVertex, endVertex);
    }

    long countBestPathTiles() {
        ShortestPathAlgorithm<PointAndDirection, DefaultEdge> algorithm = new DijkstraShortestPath<>(graph);
        var pathsFromStart = algorithm.getPaths(startVertex);
        var pathsFromEnd = algorithm.getPaths(endVertex);
        
        var lowestScore = pathsFromStart.getWeight(endVertex);
        
        return graph.vertexSet()
                .stream()
                .filter(vertex -> pathsFromStart.getWeight(vertex) + pathsFromEnd.getWeight(vertex.reverseDirection()) == lowestScore)
                .map(PointAndDirection::point)
                .distinct()
                .count();
    }
}
