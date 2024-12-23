package nl.mvdr.adventofcode.adventofcode2024.day23;

import java.util.Set;
import java.util.stream.Stream;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
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
        
        return graph.vertexSet()
                .stream()
                .flatMap(firstComputer -> Graphs.neighborListOf(graph, firstComputer)
                        .stream()
                        .flatMap(secondComputer -> Graphs.neighborListOf(graph, firstComputer)
                                .stream()
                                .filter(thirdComputer -> graph.containsEdge(secondComputer, thirdComputer))
                                .map(thirdComputer -> Set.of(firstComputer, secondComputer, thirdComputer))))
                .filter(interConnectedSet -> interConnectedSet.stream().anyMatch(computer -> computer.startsWith("t")))
                .distinct()
                .count();
    }
    
    public static void main(String[] args) {
        var instance = new Part1();

        var result = instance.solve("input-day23-2024.txt");

        LOGGER.info(result);
    }
}
 