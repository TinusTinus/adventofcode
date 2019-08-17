package nl.mvdr.adventofcode.adventofcode2018.day10;

import nl.mvdr.adventofcode.point.Point;

/**
 * Representation of a single star.
 *
 * @author Martijn van de Rijdt
 */
class Star {
    private final Point location;
    
    private final Point velocity;
    
    /**
     * Constructor.
     * 
     * @param location location
     * @param velocity velocity (represented as a point)
     */
    Star(Point location, Point velocity) {
        super();
        this.location = location;
        this.velocity = velocity;
    }
    
    /** @return current location of this star */
    Point getLocation() {
        return location;
    }
    
    /**
     * Computes the new location of this star after a second has passed.
     * 
     * @return updated star
     */
    Star tick() {
        Point newLocation = location.translate(velocity);
        return new Star(newLocation, velocity);
    }
    
    @Override
    public String toString() {
        return "[" + location + ", v=" + velocity + "]";
    }
}
