package nl.mvdr.adventofcode.adventofcode2023.day08;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * A node in the network.
 *
 * @author Martijn van de Rijdt
 */
record Node(String name, Map<Instruction, String> edges) {
    
    /**
     * Parses the textual representation of nodes.
     * 
     * @param lines list of lines from the puzzle input, where each line represents a node
     * @return nodes
     */
    static Set<Node> parse(List<String> lines) {
        return lines.stream()
                .map(Node::parse)
                .collect(Collectors.toSet());
    }
    
    /**
     * Parses the textual representation of a node.
     * 
     * @param text textual representation of a node, for example: "AAA = (BBB, CCC)"
     * @return the node
     */
    private static Node parse(String text) {
        var parts = text.split(" = ");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Unable to parse node " + text);
        }
        var name = parts[0];
        Map<Instruction, String> edges = parseEdges(parts[1]);
        
        return new Node(name, edges);
    }

    /**
     * Parses the textual representation of the edges of a node.
     * 
     * @param edgesString string representation of the edges, for example: "(BBB, CCC)"
     * @return edges
     */
    private static Map<Instruction, String> parseEdges(String edgesString) {
        if (!edgesString.startsWith("(") || !edgesString.endsWith(")")) {
            throw new IllegalArgumentException("Unable to parse edges, brackets are missing: " + edgesString);
        }
        var parts = edgesString.substring(1, edgesString.length() - 1).split(", ");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Unexpected number of edges: " + edgesString);
        }
        
        return Map.of(Instruction.LEFT, parts[0], Instruction.RIGHT, parts[1]);
    }
}
