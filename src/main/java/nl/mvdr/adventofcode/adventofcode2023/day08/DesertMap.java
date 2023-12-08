package nl.mvdr.adventofcode.adventofcode2023.day08;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.apache.commons.math3.util.ArithmeticUtils;

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
    long computePathLength() {
        return computePathLength(findNode("AAA"), node -> "ZZZ".equals(node.name()));
    }
    
    /**
     * @return length of the path from **A to **Z
     */
    long computeGhostPathLength() {
        var startNodes = findNodes(name -> name.endsWith("A"));
        var endNodes = findNodes(name -> name.endsWith("Z"));
        return startNodes.stream()
                .mapToLong(startNode -> computePathLength(startNode, endNodes::contains))
                .reduce(ArithmeticUtils::lcm)
                .orElseThrow();
    }
    
    /**
     * Finds the length of the path from the given start node to the given end node.
     * 
     * @param start start node
     * @param end predicate specifying whether a node is an end node
     * @return length of the path
     */
    private long computePathLength(Node start, Predicate<Node> end) {
        var result = 0L;
        var currentNode = start;
        while (!end.test(currentNode)) {
            var instruction = instructions.get((int)(result % instructions.size()));
            var nextNodeName = currentNode.edges().get(instruction);
            currentNode = findNode(nextNodeName);
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
