package nl.mvdr.adventofcode.adventofcode2020.day12;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.point.Point;
import nl.mvdr.adventofcode.point.TurnDirection;

/**
 * Rotates the waypoint a number of degrees.
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
        if (!(angle % 90 == 0) || 360 < angle) {
            throw new IllegalStateException("Invalid angle: " + angle);
        }
        
        // Convert to clockwise 
        int clockwiseAngle;
        if (turnDirection == TurnDirection.LEFT) {
            clockwiseAngle = 360 - angle;
        } else {
            clockwiseAngle = angle;
        }
        
        Point waypoint = startingPoint.waypoint();
        for (int i = 0; i != clockwiseAngle; i+= 90) {
            waypoint = new Point(-waypoint.y(), waypoint.x());
        }
        
        return new Ship(startingPoint.location(), startingPoint.direction(), waypoint);
    }

}
