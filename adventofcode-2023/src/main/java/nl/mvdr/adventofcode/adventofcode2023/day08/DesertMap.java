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
        var startNode = findNode("AAA");
        var endNode = findNode("ZZZ");
        return computePathLength(startNode, Set.of(endNode));
    }
    
    /**
     * @return length of the path from **A to **Z
     */
    long computeGhostPathLength() {
        var startNodes = findNodes(name -> name.endsWith("A"));
        var endNodes = findNodes(name -> name.endsWith("Z"));
        return startNodes.parallelStream()
                .mapToLong(startNode -> computePathLength(startNode, endNodes))
                .reduce(ArithmeticUtils::lcm)
                .orElseThrow(() -> new IllegalStateException("No nodes found whose name ends with 'A'"));
    }
    
    /**
     * Finds the length of the path from the given start node to any of the given end nodes.
     * 
     * @param start start node
     * @param end end nodes
     * @return length of the path
     */
    private long computePathLength(Node start, Set<Node> end) {
        var result = 0L;
        var currentNode = start;
        while (!end.contains(currentNode)) {
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
        var nodes = findNodes(name::equals);
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
