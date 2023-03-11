package nl.mvdr.adventofcode.adventofcode2022.day09;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.point.Direction;

/**
 * A single motion of the rope's head.
 * 
 * @param direction the direction of the motion
 * @param steps number of steps to take in the direction
 * @author Martijn van de Rijdt
 */
record Motion(Direction direction, int steps) {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(Motion.class);
    
    /**
     * Parses the string representation of a motion, that is, a line from the puzzle input.
     * 
     * @param stringRepresentation string representation of a motion, for example, "R 4"
     * @return motion
     */
    static Motion parse(String stringRepresentation) {
        var parts = stringRepresentation.split(" ");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Unable to parse motion: " + stringRepresentation);
        }
        var direction = Direction.parse(parts[0]);
        var steps = Integer.parseInt(parts[1]);
        return new Motion(direction, steps);
    }
    
    /**
     * Performs this motion.
     * 
     * @param rope start state of the rope
     * @return end state of the rope
     */
    Rope perform(Rope rope) {
        LOGGER.debug("Performing motion: {}", this);
        var result = rope;
        for (int i = 0; i != steps; i++) {
            result = result.step(direction);
        }
        return result;
    }
}
