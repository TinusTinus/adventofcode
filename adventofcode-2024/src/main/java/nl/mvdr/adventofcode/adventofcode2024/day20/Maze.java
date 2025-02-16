package nl.mvdr.adventofcode.adventofcode2024.day20;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.point.Point;

record Maze(Set<Point> tracks, Point start, Point end) {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(Maze.class);
    
    static Maze parse(List<String> input) {
        Set<Point> tracks = new HashSet<>();
        Set<Point> starts = new HashSet<>();
        Set<Point> ends = new HashSet<>();
        
        Point.parse2DMap(input, (point, character) -> {
            if (character == '.') {
                tracks.add(point);
            } else if (character == 'S') {
                starts.add(point);
                tracks.add(point);
            } else if (character == 'E') {
                ends.add(point);
                tracks.add(point);
            } else if (character != '#') {
                throw new IllegalArgumentException("Unable to parse as a maze element: " + character);
            }
        });
        
        if (starts.size() != 1 || ends.size() != 1) {
            throw new IllegalArgumentException("Unable to parse input as a maze: the maze requires exactly one start and one finish");
        }
        
        var start = starts.iterator().next();
        var end = ends.iterator().next();
        
        return new Maze(tracks, start, end);
    }
    
    /// Counts the number of cheats lasting at most the given duration, which would save at least the given threshold.
    /// All times are in picoseconds.
    long countCheats(int maxCheatDuration, int minSavingsThreshold) {
        ShortestPathAlgorithm<Point, DefaultEdge> algorithm = createAlgorithm();
        
        LOGGER.debug("Created shortest path algorithm");
        
        var pathsFromStart = algorithm.getPaths(start);
        var pathsFromEnd = algorithm.getPaths(end);
        
        LOGGER.debug("Computed all shortest paths");
        
        var fastestPathWithoutCheating = pathsFromStart.getPath(end).getLength();
        var maxPathLengthWithCheats = fastestPathWithoutCheating - minSavingsThreshold;
        
        return tracks.parallelStream()
                .flatMap(cheatStart -> tracks.stream()
                        .filter(cheatEnd -> !cheatStart.equals(cheatEnd))
                        .filter(cheatEnd -> cheatEnd.manhattanDistance(cheatStart) <= maxCheatDuration)
                        .filter(cheatEnd -> pathsFromStart.getPath(cheatStart).getLength()
                                + cheatStart.manhattanDistance(cheatEnd)
                                + pathsFromEnd.getPath(cheatEnd).getLength() <= maxPathLengthWithCheats))
                .count();
    }
    
    private ShortestPathAlgorithm<Point, DefaultEdge> createAlgorithm() {
        var graph = createGraph();
        return new DijkstraShortestPath<>(graph);
    }
    
    private Graph<Point, DefaultEdge> createGraph() {
        Graph<Point, DefaultEdge> result = new SimpleGraph<>(DefaultEdge.class);
        
        tracks.forEach(result::addVertex);
        
        result.vertexSet()
                .forEach(vertex -> vertex.neighbours()
                        .stream()
                        .filter(result.vertexSet()::contains)
                        .forEach(neighbour -> result.addEdge(vertex, neighbour)));
        
        return result;
    }
}
