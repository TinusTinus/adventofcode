package nl.mvdr.adventofcode.adventofcode2016.day24;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

public class Part1 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(Part1.class);

    @Override
    public int solve(Stream<String> lines) {
        var distances = calculateDistances(lines.toList());

        return 3; // TODO implement
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
    
    public static void main(String[] args) {
        Part1 instance = new Part1();

        String result = instance.solve("input-day24.txt");

        LOGGER.info(result);
    }
}
