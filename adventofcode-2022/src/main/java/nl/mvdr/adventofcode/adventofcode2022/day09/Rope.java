package nl.mvdr.adventofcode.adventofcode2022.day09;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.point.Direction;
import nl.mvdr.adventofcode.point.Point;

/**
 * Current state of the rope.
 *
 * @param knots the knots of this rope; the first knot is the rope's head, the last its tail
 * @param visited locations that have been visited by the tail
 * 
 * @author Martijn van de Rijdt
 */
record Rope(List<Point> knots, Set<Point> visited) {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(Rope.class);
    
    /**
     * Constructor for the initial state of the rope.
     * 
     * @param length length of the rope
     */
    Rope(int length) {
        this(Collections.nCopies(length, Point.ORIGIN), Set.of(Point.ORIGIN));
    }
    
    /**
     * Performs a single step in the given direction.
     * 
     * @param direction direction to step to
     * @return updated state of the rope
     */
    Rope step(Direction direction) {
        List<Point> newKnots = new ArrayList<>();
        
        // First move the head.
        var newKnot = direction.move(knots.get(0));
        newKnots.add(newKnot);
        
        // Move the other knots, in order
        for (int i = 1; i != knots.size(); i++) {
            var knot = knots.get(i);
            
            if (newKnot.neighboursIncludingDiagonals().contains(knot)) {
                newKnot = knot;
            } else {
                // Move the knot
                newKnot = new Point(
                        knot.x() + Integer.signum(newKnot.x() - knot.x()),
                        knot.y() + Integer.signum(newKnot.y() - knot.y()));
            }
            newKnots.add(newKnot);
        }
        
        Set<Point> newVisited = new HashSet<>(visited);
        newVisited.add(newKnot);
        
        var result = new Rope(newKnots, newVisited);
        LOGGER.debug("Updated rope after performing a step in direction {}: {}", direction, result);
        return result;
    }
}
