package nl.mvdr.adventofcode.adventofcode2020.day12;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * An instruction for the suhip.
 *
 * @author Martijn van de Rijdt
 */
@FunctionalInterface
interface Instruction {
    
    /**
     * Parses a textual representation of a list of instructions.
     * 
     * @param lines puzzle input as a stream of lines
     * @return instructions
     */
    static List<Instruction> parse(Stream<String> lines) {
        return lines.filter(Predicate.not(String::isEmpty))
                .map(Instruction::parseInstruction)
                .collect(Collectors.toList());
    }
    
    /**
     * Parses a textual representation of an instruction.
     * 
     * @param line textual representation of an instruction
     * @return instruction
     */
    private static Instruction parseInstruction(String line) {
        char letter = line.charAt(0);
        int argument = Integer.parseInt(line.substring(1));
        
        Optional<Instruction> result = MoveForward.of(letter, argument);
        if (result.isEmpty()) {
            // Move.of considers 'R' a valid value.
            // Make sure to try Turn before Move, to prevent 'R' from being interpreted as a Move to the east.
            result = Turn.of(letter, argument);
        }
        if (result.isEmpty()) {
            result = Move.of(letter, argument);
        }
        return result.orElseThrow(() -> new IllegalArgumentException("Cannot parse instruction: " + line));
    }
    
    /**
     * Executes this instruction.
     * 
     * @param startingPoint starting position and direction of the ship
     * @return end position and direction of the ship
     */
    Ship execute(Ship startingPoint);


}
