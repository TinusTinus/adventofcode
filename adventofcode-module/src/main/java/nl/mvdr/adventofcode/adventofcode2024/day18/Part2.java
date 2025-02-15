package nl.mvdr.adventofcode.adventofcode2024.day18;

import java.util.stream.Stream;

import org.jgrapht.Graph;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.point.Point;
import nl.mvdr.adventofcode.solver.LinesSolver;

public class Part2 implements LinesSolver<Point> {

    private static final Logger LOGGER = LoggerFactory.getLogger(Part2.class);

    private final int maxCoordinateValue;
    
    Part2(int maxCoordinateValue) {
        this.maxCoordinateValue = maxCoordinateValue;
    }
    
    public Part2() {
        this(70);
    }
    
    @Override
    public Point solve(Stream<String> lines) {
        Graph<Point, DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);
        Point.points(0, maxCoordinateValue, 0, maxCoordinateValue)
                .forEach(graph::addVertex);
        graph.vertexSet()
                .forEach(point -> point.neighbours()
                        .stream()
                        .filter(graph::containsVertex)
                        .forEach(neighbour -> graph.addEdge(point, neighbour)));
        
        var start = new Point(0, 0);
        var end = new Point(maxCoordinateValue, maxCoordinateValue);
        
        return lines.map(Point::parse)
                .peek(graph::removeVertex)
                .filter(b -> !new ConnectivityInspector<>(graph).pathExists(start, end))
                .findFirst()
                .orElseThrow();
    }
    
    public static void main(String[] args) {
        var instance = new Part2();

        var result = instance.solve("input-day18-2024.txt");

        LOGGER.info(result);
    }
}
 