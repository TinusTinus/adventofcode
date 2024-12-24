package nl.mvdr.adventofcode.adventofcode2024.day23;

import java.util.stream.Stream;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import nl.mvdr.adventofcode.solver.LinesSolver;

abstract class LanPartySolver<R> implements LinesSolver<R> {

    @Override
    public R solve(Stream<String> lines) {
        Graph<String, DefaultEdge> network = new SimpleGraph<>(DefaultEdge.class);
        lines.forEach(line -> addConnection(network, line));
        return solve(network);
    }
    
    /// Adds a connection to the network.
    ///
    /// @param network a graph representing the network built up so far
    /// @param line a single line from the puzzle input, representing a connection between two computers; for example: "kh-tc"
    private static void addConnection(Graph<String, DefaultEdge> network, String line) {
        var computers = Stream.of(line.split("-")).toList();
        if (computers.size() != 2) {
            throw new IllegalArgumentException("Unable to parse line: " + line);
        }
        computers.forEach(network::addVertex);
        network.addEdge(computers.getFirst(), computers.getLast());
    }
    
    abstract R solve(Graph<String, DefaultEdge> network);
}
 