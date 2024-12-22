package nl.mvdr.adventofcode.adventofcode2024.day20;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import nl.mvdr.adventofcode.point.Point;

record Maze(Set<Point> walls, Set<Point> tracks, Point start, Point end) {
    
    static Maze parse(List<String> input) {
        Set<Point> walls = new HashSet<>();
        Set<Point> tracks = new HashSet<>();
        Set<Point> starts = new HashSet<>();
        Set<Point> ends = new HashSet<>();
        
        Point.parse2DMap(input, (point, character) -> {
            if (character == '#') {
                walls.add(point);
            } else if (character == '.') {
                tracks.add(point);
            } else if (character == 'S') {
                starts.add(point);
                tracks.add(point);
            } else if (character == 'E') {
                ends.add(point);
                tracks.add(point);
            } else {
                throw new IllegalArgumentException("Unable to parse as a maze element: " + character);
            }
        });
        
        if (starts.size() != 1 || ends.size() != 1) {
            throw new IllegalArgumentException("Unable to parse input as a maze: the maze requires exactly one start and one finish");
        }
        
        var start = starts.iterator().next();
        var end = ends.iterator().next();
        
        return new Maze(walls, tracks, start, end);
    }
    
    /// Counts the number of cheats which would save at least the given threshold in picoseconds.
    long countCheats(int minSavingsThreshold) {
        var fastestPathWithoutCheating = fastestPath();
        
        return walls.stream()
                .parallel()
                .map(this::removeWall)
                .mapToInt(Maze::fastestPath)
                .filter(pathLength -> minSavingsThreshold <= fastestPathWithoutCheating - pathLength)
                .count();
    }
    
    private int fastestPath() {
        var graph = createGraph();
        ShortestPathAlgorithm<Point, DefaultEdge> algorithm = new DijkstraShortestPath<>(graph);
        return algorithm.getPath(start, end).getLength();
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

    private Maze removeWall(Point wall) {
        Set<Point> newWalls = new HashSet<>(walls);
        newWalls.remove(wall);
        
        Set<Point> newTracks = new HashSet<>(tracks);
        newTracks.add(wall);
        
        return new Maze(newWalls, newTracks, start, end);
    }
    
}
