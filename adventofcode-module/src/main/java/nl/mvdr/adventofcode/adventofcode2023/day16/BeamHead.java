package nl.mvdr.adventofcode.adventofcode2023.day16;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

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
    static final BeamHead START = new BeamHead(0, 0, Direction.RIGHT);
    
    /**
     * Finds the possible starting points for a beam, at the edges of a contraption.
     * 
     * @param maxX maximum x coordinate value in the contraption
     * @param maxY maximum y coordinate value in the contraption
     * @return starting points
     */
    static Set<BeamHead> findStartingPoints(int maxX, int maxY) {
        Set<BeamHead> result = new HashSet<>();
        IntStream.range(0, maxX + 1)
                .peek(x -> result.add(new BeamHead(x, 0, Direction.DOWN))) // Starting from the top
                .forEach(x -> result.add(new BeamHead(x, maxY, Direction.UP))); // Starting from the bottom
        IntStream.range(0, maxY + 1)
                .peek(y -> result.add(new BeamHead(0, y, Direction.RIGHT))) // Starting from the left
                .forEach(y -> result.add(new BeamHead(maxX, y, Direction.LEFT))); // Starting from the right
        return result;
    }
    
    /**
     * Convenience constructor.
     * 
     * @param x x coordinate
     * @param y y coordinate
     * @param direction direction
     */
    private BeamHead(int x, int y, Direction direction) {
        this(new Point(x, y), direction);
    }
}
