package nl.mvdr.adventofcode.adventofcode2018.day13;

import nl.mvdr.adventofcode.point.Point;

/**
 * Exception indicating that a collision occurred when attempting to perform a tick.
 *
 * @author Martijn van de Rijdt
 */
@SuppressWarnings("serial") // this non-public class is not intended for serialization
class CollisionException extends RuntimeException {
    
    private final Point location;
    
    /**
     * Constructor.
     * 
     * @param location the location of the collision
     */
    CollisionException(Point location) {
        super("Collision occurred at " + location);
        
        this.location = location;
    }
    
    Point getLocation() {
        return location;
    }
}
