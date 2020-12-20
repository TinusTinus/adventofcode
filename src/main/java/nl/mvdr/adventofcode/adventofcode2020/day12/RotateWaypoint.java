package nl.mvdr.adventofcode.adventofcode2020.day12;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.point.Direction;
import nl.mvdr.adventofcode.point.TurnDirection;

/**
 * Turns the ship a number of degrees.
 *
 * @author Martijn van de Rijdt
 */
record RotateWaypoint(TurnDirection turnDirection, int angle) implements Instruction {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(RotateWaypoint.class);

    /**
     * Attempts to create a new turn action, based on the given information.
     * 
     * @param letter representation of the turn direction, for example: 'R' for clockwise
     * @param angle angle to turn in degrees, for example: 90
     * @return turn action, or empty if the given letter does not represent a valid turn direction
     */
    static Optional<Instruction> of(char letter, int angle) {
        Optional<Instruction> result;
        try {
            TurnDirection turnDirection = TurnDirection.parse(letter);
            result = Optional.of(new RotateWaypoint(turnDirection, angle));
        } catch (IllegalArgumentException e) {
            LOGGER.debug("Invalid turn direction: " + letter, e);
            result = Optional.empty();
        }
        return result;
    }
    
    /** {@inheritDoc} */
    @Override
    public Ship execute(Ship startingPoint) {
        if (!(angle % 90 == 0)) {
            throw new IllegalStateException("Invalid angle: " + angle);
        }
        
        // TODO implement
        return startingPoint;
    }

}
