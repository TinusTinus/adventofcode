package nl.mvdr.adventofcode.adventofcode2022.day22;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nl.mvdr.adventofcode.point.Point;

/**
 * Representation of a path.
 *
 * @author Martijn van de Rijdt
 */
record Path(List<Instruction> instructions) implements Instruction {
    
    /**
     * Parses the string representation of a path.
     * 
     * @param stringRepresentation string representation of a path, for example: "10R5L5R10L4R5L5"
     * @return path
     */
    static Path parse(String stringRepresentation) {
        var remainingString = stringRepresentation;
        List<Instruction> instructions = new ArrayList<>();
        while (!remainingString.isEmpty()) {
            if (Character.isAlphabetic(remainingString.charAt(0))) {
                instructions.add(TurnInstruction.parse(remainingString.charAt(0)));
                remainingString = remainingString.substring(1);
            } else {
                var digits = 1;
                while (digits < remainingString.length() && Character.isDigit(remainingString.charAt(digits))) {
                    digits++;
                }
                instructions.add(MovementInstruction.parse(remainingString.substring(0, digits)));
                remainingString = remainingString.substring(digits);
            }
        }
        return new Path(instructions);
    }
    
    @Override
    public Position execute(Position startingPosition, Map<Point, Terrain> map) {
        var result = startingPosition;
        for (Instruction instruction : instructions) {
            result = instruction.execute(result, map);
        }
        return result;
    }
}
