package nl.mvdr.adventofcode.adventofcode2023.day18;

import nl.mvdr.adventofcode.point.Direction;
import nl.mvdr.adventofcode.point.Point;

/**
 * A single instruction in a dig plan.
 * 
 * Note that colors are entirely irrelevant to the puzzle.
 *
 * @param direction the direction to dig in
 * @param distance the distance to dig, in meters
 * @author Martijn van de Rijdt
 */
record DigPlanInstruction(Direction direction, int distance) {
    
    private static final int HEXADECIMAL_RADIX = 16;

    /**
     * Parses the textual representation of an instruction.
     * 
     * @param text line from the dig plan, for example: "R 6 (#70c710)"
     * @param hexValueAsColor whether the hex value ("#70c710" in the example) should be interpreted as a color;
     *      if not, it is interpreted as the representation of distance and direction
     * @return instruction
     */
    static DigPlanInstruction parse(String text, boolean hexValueAsColor) {
        Direction direction;
        int distance;
        if (hexValueAsColor) {
            var parts = text.split(" ");
            if (parts.length != 3) {
                throw new IllegalArgumentException("Invalid instruction: " + text);
            }
            direction = Direction.parse(parts[0]);
            distance = Integer.parseInt(parts[1]);
        } else {
            var poundSignIndex = text.indexOf("#");
            var closingBracketIndex = text.indexOf(")");
            
            var distanceString = text.substring(poundSignIndex + 1, closingBracketIndex - 1);
            distance = Integer.parseInt(distanceString, HEXADECIMAL_RADIX);
            
            var directionString = text.substring(closingBracketIndex - 1, closingBracketIndex);
            direction = Direction.parse(directionString);
        }
        
        return new DigPlanInstruction(direction, distance);
    }
    
    /**
     * Digs a trench according to this instruction.
     * 
     * @param startingPoint starting point
     * @return end point of the trench
     */
    Point dig(Point startingPoint) {
        return direction.move(startingPoint, distance);
    }
}
