package nl.mvdr.adventofcode.adventofcode2017.day19;

import nl.mvdr.adventofcode.point.Direction;
import nl.mvdr.adventofcode.point.Point;

/**
 * Representation of the current state of the packet.
 *
 * @author Martijn van de Rijdt
 */
class Packet {
    
    private final Point location;
    private final Direction direction;

    /**
     * Constructor.
     * 
     * @param location the packet's current location
     * @param direction direction the packet is currently facing
     */
    Packet(Point location, Direction direction) {
        super();
        this.location = location;
        this.direction = direction;
    }
    
    Point getLocation() {
        return location;
    }
    
    Direction getDirection() {
        return direction;
    }

    @Override
    public String toString() {
        return "[" + location + ", " + direction + "]";
    }
}
