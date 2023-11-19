package nl.mvdr.adventofcode.adventofcode2021.day02;

import java.util.List;
import java.util.stream.Stream;

import nl.mvdr.adventofcode.point.Point;

/**
 * Representation of a single command.
 *
 * @author Martijn van de Rijdt
 */
record Command(Instruction instruction, int distance) {
    
    /**
     * Parses the textual representation of a list of commands.
     * 
     * @param input puzzle input
     * @return the commands
     */
    static List<Command> parse(Stream<String> input) {
        return input.map(Command::parse)
                .toList();
    }
    
    /**
     * Parses the textual representation of a command.
     * 
     * @param text representation of a command, such as "forward 1", "down 2", or "up 3"
     * @return the command
     */
    private static Command parse(String text) {
        var parts = text.split(" ");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Unable to parse command: " + text);
        }
        
        var instruction = Instruction.of(parts[0]);
        var distance = Integer.parseInt(parts[1]);
        return new Command(instruction, distance);
    }
    
    /**
     * Performs this command.
     * 
     * @param startingLocation starting point
     * @return end point
     */
    Point execute(Point startingLocation) {
        return instruction.move(startingLocation, distance);
    }
}
