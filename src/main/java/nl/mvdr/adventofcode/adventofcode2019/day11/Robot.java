package nl.mvdr.adventofcode.adventofcode2019.day11;

import nl.mvdr.adventofcode.point.Direction;
import nl.mvdr.adventofcode.point.Point;
import nl.mvdr.adventofcode.point.TurnDirection;

/**
 * Representation of a hull-painting robot.
 *
 * @author Martijn van de Rijdt
 */
class Robot {
    private final Point location;
    
    private final Direction direction;
    
    /** Constructor. */
    Robot() {
        this(new Point(0, 0), Direction.UP);
    }
    
    /**
     * Constructor.
     * 
     * @param location current location on the hull
     * @param direction direction the robot is facing
     */
    private Robot(Point location, Direction direction) {
        super();
        this.location = location;
        this.direction = direction;
    }
    
    /** @return current location on the hull */
    Point getLocation() {
        return location;
    }
    
    /**
     * Turns in the given turn direction and then moves a single panel.
     * 
     * @param turnDirection turn direction
     * @return updated state of the robot
     */
    Robot turnAndMove(TurnDirection turnDirection) {
        Direction newDirection = turnDirection.turn(direction);
        Point newLocation = newDirection.move(location);
        return new Robot(newLocation, newDirection);
    }
}
