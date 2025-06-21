package nl.mvdr.adventofcode.adventofcode2016.day24;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.jgrapht.Graph;
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultUndirectedGraph;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.point.Point;
import nl.mvdr.adventofcode.solver.IntSolver;

public class Part2 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(Part2.class);

    @Override
    public int solve(Stream<String> lines) {
        var distances = calculateDistances(lines.toList());

        var remainingLocations = distances.keySet()
                .stream()
                .map(Path::from)
                .filter(location -> location.intValue() != 0)
                .collect(Collectors.toSet());
        
        return shortestPathLength(distances, 0, remainingLocations);
    }

    /// Determines the distance between each pair of numbered locations.
    private Map<Path, Integer> calculateDistances(List<String> lines) {
        Map<Integer, Point> locationsOfInterest = new HashMap<>();
        Graph<Point, DefaultEdge> graph = new DefaultUndirectedGraph<>(DefaultEdge.class);
        Point.parse2DMap(lines, (point, character) -> {
            if (Character.isDigit(character)) {
                int number = Integer.parseInt("" + character);
                locationsOfInterest.put(Integer.valueOf(number), point);
                graph.addVertex(point);
            } else if (character == '.') {
                graph.addVertex(point);
            } else if (character != '#') {
                throw new IllegalArgumentException("Unable to parse input containing " + character);
            }
        });
        
        graph.vertexSet()
            .stream()
            .forEach(vertex -> vertex.neighbours()
                .stream()
                .filter(graph::containsVertex)
                .forEach(neighbour -> graph.addEdge(vertex, neighbour)));

        ShortestPathAlgorithm<Point, DefaultEdge> shortestPathAlgorithm = new DijkstraShortestPath<>(graph);
        
        return locationsOfInterest.keySet()
                .stream()
                .flatMap(from -> locationsOfInterest.keySet()
                        .stream()
                        .filter(to -> !from.equals(to))
                        .map(to -> new Path(from, to)))
                .collect(Collectors.toMap(Function.identity(), path -> 
                shortestPathAlgorithm.getPath(locationsOfInterest.get(path.from()), locationsOfInterest.get(path.to())).getLength()));
    }
    
    /// Determines the length of the shortest path visiting all of the given remaining locations from the given current location.
    private int shortestPathLength(Map<Path, Integer> distances, int currentLocation, Set<Integer> remainingLocations) {
        int result;
        if (remainingLocations.isEmpty()) {
            result = distances.get(new Path(currentLocation, 0)).intValue();
        } else {
            result = remainingLocations.stream()
                    .mapToInt(Integer::valueOf)
                    .map(nextLocation -> {
                        var distance = distances.get(new Path(currentLocation, nextLocation)).intValue();
                        var newRemainingLocations = remainingLocations.stream()
                                .filter(location -> location.intValue() != nextLocation)
                                .collect(Collectors.toSet());
                        return distance + shortestPathLength(distances, nextLocation, newRemainingLocations);
                    })
                    .min()
                    .orElseThrow();
        }
        return result;
    }
    
    public static void main(String[] args) {
        Part2 instance = new Part2();

        String result = instance.solve("input-day24.txt");

        LOGGER.info(result);
    }
}
