package nl.mvdr.adventofcode.adventofcode2023.day16;

import nl.mvdr.adventofcode.point.Direction;
import nl.mvdr.adventofcode.point.Point;

/**
 * The head of a beam of light.
 *
 * @author Martijn van de Rijdt
 */
record BeamHead(Point location, Direction direction) {
    
    /**
     * Start of the beam.
     */
    static final BeamHead START = new BeamHead(new Point(0, 0), Direction.RIGHT);
}
