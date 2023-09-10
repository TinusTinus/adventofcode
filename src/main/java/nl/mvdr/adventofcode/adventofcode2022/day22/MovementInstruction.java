package nl.mvdr.adventofcode.adventofcode2022.day22;

import java.util.Map;

import nl.mvdr.adventofcode.point.Point;

/**
 * Indicates the number of tiles to move in the direction you are facing.
 * 
 * If you run into a wall, you stop moving forward and continue with the next
 * instruction.
 *
 * @param tiles the number of tiles to move
 * @author Martijn van de Rijdt
 */
record MovementInstruction(int tiles) implements Instruction {
    
    /**
     * Parses a string representation of a movement instruction.
     * 
     * @param stringRepresentation string representation of the number of tiles to move, for example: "10"
     * @return movement instruction
     */
    static MovementInstruction parse(String stringRepresentation) {
        var tiles = Integer.parseInt(stringRepresentation);
        return new MovementInstruction(tiles);
    }
    
    @Override
    public Position execute(Position startingPosition, Map<Point, Terrain> map) {
        // TODO implement!
        return startingPosition;
    }
}
