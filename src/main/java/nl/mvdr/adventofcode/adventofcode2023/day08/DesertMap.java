package nl.mvdr.adventofcode.adventofcode2023.day08;

import java.util.List;
import java.util.Set;

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
        var result = 0;
        var currentNode = findNode("AAA");
        while (!currentNode.name().equals("ZZZ")) {
            var instruction = instructions.get(result % instructions.size());
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
        return network.stream()
                .filter(node -> name.equals(node.name()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Node not found: " + name));
    }
}
