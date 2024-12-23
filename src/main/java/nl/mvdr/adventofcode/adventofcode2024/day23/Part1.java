package nl.mvdr.adventofcode.adventofcode2024.day23;

import java.util.Set;
import java.util.stream.Stream;

import org.apache.commons.math3.util.CombinatoricsUtils;
import org.jgrapht.Graph;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.solver.LongSolver;

public class Part1 implements LongSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(Part1.class);

    @Override
    public long solve(Stream<String> lines) {
        Graph<String, DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);
        lines.forEach(line -> {
            var computers = Stream.of(line.split("-")).toList();
            if (computers.size() != 2) {
                throw new IllegalArgumentException("Unable to parse line: " + line);
            }
            computers.forEach(graph::addVertex);
            graph.addEdge(computers.getFirst(), computers.getLast());
        });
        
        var connectivityInspector = new ConnectivityInspector<>(graph);
        
        return graph.vertexSet()
                .stream()
                .filter(computer -> computer.startsWith("t"))
                .map(connectivityInspector::connectedSetOf)
                .peek(connectedSet -> LOGGER.info("Connected set: {}", connectedSet)) // TODO
                .mapToInt(Set::size)
                .mapToLong(connectedSetSize -> CombinatoricsUtils.binomialCoefficient(connectedSetSize, 3))
                .sum();
    }
    
    public static void main(String[] args) {
        var instance = new Part1();

        var result = instance.solve("input-day23-2024.txt");

        LOGGER.info(result);
    }
}
 