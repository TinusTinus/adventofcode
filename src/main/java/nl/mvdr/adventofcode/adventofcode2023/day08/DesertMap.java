package nl.mvdr.adventofcode.adventofcode2023.day08;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Representation of a desert map.
 *
 * @author Martijn van de Rijdt
 */
record DesertMap(List<Instruction> instructions, Set<Node> network) {
    /**
     * Parses the puzzle input.
     * 
     * @param lines puzzle input
     * @return the desert map represented by the given input
     */
    static DesertMap parse(List<String> lines) {
        List<Instruction> instructions = Instruction.parse(lines.get(0));
        
        if (!lines.get(1).isEmpty()) {
            throw new IllegalArgumentException("Unexpected contents found on second line: " + lines.get(1));
        }
        
        Set<Node> network = Node.parse(lines.subList(2, lines.size()));
        
        return new DesertMap(instructions, network);
    }
    
    /**
     * @return length of the path from AAA to ZZZ
     */
    int computePathLength() {
        return computePathLength(
                name -> "AAA".equals(name),
                name -> "ZZZ".equals(name));
    }
    
    /**
     * @return length of the path from **A to **Z
     */
    int computeGhostPathLength() {
        return computePathLength(
                name -> name.endsWith("A"),
                name -> name.endsWith("Z"));
    }
    
    /**
     * First the length of a path through the desert.
     * 
     * @param start predicate specifying node names of the start nodes
     * @param end predicate specifying node names of the end nodes
     * @return length of the path from the start nodes to the end nodes
     */
    private int computePathLength(Predicate<String> start, Predicate<String> end) {
        var result = 0;
        var currentNodes = findNodes(start);
        
        while (!currentNodes.stream().allMatch(node -> end.test(node.name()))) {
            var instruction = instructions.get(result % instructions.size());
            currentNodes = currentNodes.stream()
                    .map(node -> node.edges().get(instruction))
                    .map(this::findNode)
                    .collect(Collectors.toSet());
            result++;
        }
        
        return result;
    }

    /**
     * Finds the node with the given name.
     * 
     * @param name node name, for example: "AAA"
     * @return the node with the given name
     */
    private Node findNode(String name) {
        var nodes = findNodes(n -> n.equals(name));
        if (nodes.size() != 1) {
            throw new IllegalStateException("Node not found: " + name);
        }
        return nodes.iterator().next();
    }
    
    /**
     * Finds nodes satisfying the given predicate.
     * 
     * @param predicate string predicate
     * @return nodes satisfying the given predicate
     */
    private Set<Node> findNodes(Predicate<String> predicate) {
        return network.stream()
                .filter(node -> predicate.test(node.name()))
                .collect(Collectors.toSet());
    }
}
