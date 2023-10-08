package nl.mvdr.adventofcode.adventofcode2022.day22;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.point.Direction;
import nl.mvdr.adventofcode.point.Point;
import nl.mvdr.adventofcode.point.TurnDirection;

/**
 * Indicates whether to turn 90 degrees clockwise (R) or counterclockwise (L).
 * 
 * Turning happens in-place; it does not change your current tile.
 *
 * @author Martijn van de Rijdt
 */
enum TurnInstruction implements Instruction {
    LEFT(TurnDirection.LEFT),
    RIGHT(TurnDirection.RIGHT);
    
    private static final Logger LOGGER = LoggerFactory.getLogger(TurnInstruction.class);
    private final TurnDirection turnDirection;

    /**
     * Parses a string representation of a turn instruction.
     * 
     * @param c representation of a turn instruction: 'L' or 'R'
     * @return turn instruction
     */
    static TurnInstruction parse(char c) {
        TurnDirection direction = TurnDirection.parse(c);
        return Stream.of(TurnInstruction.values())
                .filter(instruction -> instruction.turnDirection == direction)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Failed to parse turn instruction: " + c));
    }
    
    /**
     * Constructor.
     * 
     * @param turnDirection the direction in which to turn
     */
    TurnInstruction(TurnDirection turnDirection) {
        this.turnDirection = turnDirection;
    }
    
    @Override
    public Position execute(Position startingPosition, TerrainMap map) {
        Point newLocation = startingPosition.location();
        Direction newFacing = turnDirection.turn(startingPosition.facing());
        LOGGER.debug("Turned {}; new facing: {}", this, newFacing);
        return new Position(newLocation, newFacing);
    }
}
