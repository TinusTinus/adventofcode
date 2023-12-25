package nl.mvdr.adventofcode.adventofcode2023.day25;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import org.jgrapht.Graph;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.graph.AsSubgraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultUndirectedGraph;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.IntSolver;

/**
 * Solution to <a href="https://adventofcode.com/2023/day/25">Snowverload</a>.
 *
 * @author Martijn van de Rijdt
 */
public class SnowverloadPart1 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(SnowverloadPart1.class);

    @Override
    public int solve(Stream<String> lines) {
        Graph<String, DefaultEdge> graph = parseGraph(lines);
        
        return graph.edgeSet().stream()
                .flatMap(firstEdge -> graph.edgeSet().stream()
                        .filter(secondEdge -> secondEdge != firstEdge)
                        .flatMap(secondEdge -> graph.edgeSet().stream()
                                .filter(thirdEdge -> thirdEdge != firstEdge)
                                .filter(thirdEdge -> thirdEdge != secondEdge)
                                .map(thirdEdge -> {
                                    Set<DefaultEdge> updatedEdgeSet = new HashSet<>(graph.edgeSet());
                                    updatedEdgeSet.remove(firstEdge);
                                    updatedEdgeSet.remove(secondEdge);
                                    updatedEdgeSet.remove(thirdEdge);
                                    return updatedEdgeSet;
                                })))
                .map(updatedEdgeSet -> new AsSubgraph<>(graph, graph.vertexSet(), updatedEdgeSet))
                .map(ConnectivityInspector::new)
                .map(ConnectivityInspector::connectedSets)
                .filter(connectedSets -> connectedSets.size() == 2)
                .distinct()
                .mapToInt(connectedSets -> connectedSets.stream().mapToInt(Set::size).sum())
                .reduce((result0, result1) -> {throw new IllegalStateException("Found multiple possible splits");})
                .orElseThrow(() -> new IllegalStateException("No result found"));
    }

    /**
     * Builds a graph based on the given puzzle input.
     * 
     * @param lines puzzle input
     * @return graph
     */
    private static Graph<String, DefaultEdge> parseGraph(Stream<String> lines) {
        Graph<String, DefaultEdge> graph = new DefaultUndirectedGraph<>(DefaultEdge.class);
        lines.forEach(line -> addNodesAndEdges(graph, line));
        return graph;
    }

    /**
     * Adds nodes and edges to the given graph based on the given line from the puzzle input.
     * 
     * @param graph result graph
     * @param line a line from the wiring diagram, for example: "jqt: rhn xhk nvd"
     */
    private static void addNodesAndEdges(Graph<String, DefaultEdge> graph, String line) {
        var parts = line.split(": ");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Unable to parse: " + line);
        }
        var node = parts[0];
        graph.addVertex(node);
        Stream.of(parts[1].split(" "))
                .peek(graph::addVertex)
                .forEach(connectedNode -> graph.addEdge(node, connectedNode));
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        var instance = new SnowverloadPart1();

        var result = instance.solve("input-day25-2023.txt");

        LOGGER.info(result);
    }
}
 