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
     * @param part1 whether the rules from part 1 of the puzzle apply
     * @return instructions
     */
    static List<Instruction> parse(Stream<String> lines, boolean part1) {
        return lines.filter(Predicate.not(String::isEmpty))
                .map(line -> parseInstruction(line, part1))
                .collect(Collectors.toList());
    }
    
    /**
     * Parses a textual representation of an instruction.
     * 
     * @param line textual representation of an instruction
     * @parma part1 whether the rules from part 1 of the puzzle apply
     * @return instruction
     */
    private static Instruction parseInstruction(String line, boolean part1) {
        char letter = line.charAt(0);
        int argument = Integer.parseInt(line.substring(1));

        Optional<Instruction> result;

        if (part1) {
            result = MoveForward.of(letter, argument);
        } else {
            result = MoveForwardToWaypoint.of(letter, argument);
        }

        if (result.isEmpty()) {
            // Move.of considers 'R' a valid value.
            // Make sure to try Turn / Rotate Waypoint before Move / Move Waypoint,
            // to prevent 'R' from being interpreted as a Move to the east.
            if (part1) {
                result = Turn.of(letter, argument);
            } else {
                result = RotateWaypoint.of(letter, argument);
            }
        }
        if (result.isEmpty()) {
            if (part1) {
                result = Move.of(letter, argument);
            } else {
                result = MoveWaypoint.of(letter, argument);
            }
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
