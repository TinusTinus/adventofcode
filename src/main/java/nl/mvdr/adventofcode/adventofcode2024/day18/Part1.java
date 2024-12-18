package nl.mvdr.adventofcode.adventofcode2024.day18;

import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.jgrapht.Graph;
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.point.Point;
import nl.mvdr.adventofcode.solver.IntSolver;

public class Part1 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(Part1.class);

    private final int maxCoordinateValue;
    private final int bytes;
    
    Part1(int maxCoordinateValue, int bytes) {
        this.maxCoordinateValue = maxCoordinateValue;
        this.bytes = bytes;
    }
    
    public Part1() {
        this(70, 1024);
    }
    
    @Override
    public int solve(Stream<String> lines) {
        Set<Point> fallenBytes = lines.limit(bytes)
                .map(Point::parse)
                .collect(Collectors.toSet());
        
        Graph<Point, DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);
        Point.points(0, maxCoordinateValue, 0, maxCoordinateValue)
                .filter(Predicate.not(fallenBytes::contains))
                .forEach(graph::addVertex);
        graph.vertexSet()
                .forEach(point -> point.neighbours()
                        .stream()
                        .filter(graph::containsVertex)
                        .forEach(neighbour -> graph.addEdge(point, neighbour)));
        
        ShortestPathAlgorithm<Point, DefaultEdge> algorithm = new DijkstraShortestPath<>(graph);
        var path = algorithm.getPath(new Point(0, 0), new Point(maxCoordinateValue, maxCoordinateValue));
        return path.getLength();
    }
    
    public static void main(String[] args) {
        var instance = new Part1();

        var result = instance.solve("input-day18-2024.txt");

        LOGGER.info(result);
    }
}
 