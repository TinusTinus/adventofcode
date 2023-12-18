package nl.mvdr.adventofcode.adventofcode2023.day18;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import nl.mvdr.adventofcode.point.Direction;
import nl.mvdr.adventofcode.point.Point;

/**
 * A single instruction in a dig plan.
 *
 * @param direction the direction to dig in
 * @param distance the distance to dig, in meters
 * @param color the edge's color
 * @author Martijn van de Rijdt
 */
record DigPlanInstruction(Direction direction, int distance, Color color) {
    
    /**
     * Parses the textual representation of an instruction.
     * 
     * @param text line from the dig plan, for example: "R 6 (#70c710)"
     * @return instruction
     */
    static DigPlanInstruction parse(String text) {
        var parts = text.split(" ");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Invalid instruction: " + text);
        }
        var direction = Direction.parse(parts[0]);
        var distance = Integer.parseInt(parts[1]);
        
        var colorString = parts[2];
        if (!colorString.startsWith("(") || !colorString.endsWith(")")) {
            throw new IllegalArgumentException("Invalid edge color specification: " + colorString);
        }
        colorString = colorString.substring(1, colorString.length() - 1); // lose the brackets
        var color = Color.decode(colorString);
        
        return new DigPlanInstruction(direction, distance, color);
    }
    
    /**
     * Digs a trench according to this instruction.
     * 
     * @param startingPoint starting point
     * @return points making up the newly dug trench
     */
    List<Point> perform(Point startingPoint) {
        List<Point> result = new ArrayList<>();
        var location = startingPoint;
        for (int i = 0; i != distance; i++) {
            location = direction.move(location);
            result.add(location);
        }
        return result;
    }
}
