package nl.mvdr.adventofcode.adventofcode2020.day12;

import nl.mvdr.adventofcode.point.Direction;
import nl.mvdr.adventofcode.point.Point;

/**
 * State of the ship.
 *
 * @author Martijn van de Rijdt
 * 
 */
record Ship(Point location, Direction direction) {
    
    /** Starting position of the ship. */
    static Ship STARTING_POSITION = new Ship(new Point(0, 0), Direction.RIGHT);
}
