package nl.mvdr.adventofcode.adventofcode2021.day02;

import java.util.List;
import java.util.stream.Stream;

/**
 * Representation of a single command.
 *
 * @param instruction the instruction
 * @param x the x parameter value
 * @param applyAim whether the rules from part 2 of the puzzle should be applied
 * @author Martijn van de Rijdt
 */
record Command(Instruction instruction, int x, boolean applyAim) {
    
    /**
     * Parses the textual representation of a list of commands.
     * 
     * @param input puzzle input
     * @param applyAim whether the rules from part 2 of the puzzle should be applied
     * @return the commands
     */
    static List<Command> parse(Stream<String> input, boolean applyAim) {
        return input.map(text -> parse(text, applyAim))
                .toList();
    }
    
    /**
     * Parses the textual representation of a command.
     * 
     * @param text representation of a command, such as "forward 1", "down 2", or "up 3"
     * @param applyAim whether the rules from part 2 of the puzzle should be applied
     * @return the command
     */
    private static Command parse(String text, boolean applyAim) {
        var parts = text.split(" ");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Unable to parse command: " + text);
        }
        
        var instruction = Instruction.of(parts[0]);
        var distance = Integer.parseInt(parts[1]);
        return new Command(instruction, distance, applyAim);
    }
    
    /**
     * Performs this command.
     * 
     * @param startingState the submarine's starting point
     * @return the submarine's end point
     */
    Submarine execute(Submarine startingState) {
        return instruction.execute(startingState, x, applyAim);
    }
}
