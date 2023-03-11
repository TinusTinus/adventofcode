package nl.mvdr.adventofcode.adventofcode2022.day09;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.point.Direction;
import nl.mvdr.adventofcode.point.Point;

/**
 * Current state of the rope.
 *
 * @param head current location of the rope's head
 * @param tail current location of the rope's tail
 * @param visited locations that have been visited by the tail
 * 
 * @author Martijn van de Rijdt
 */
record Rope(Point head, Point tail, Set<Point> visited) {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(Rope.class);
    
    /**
     * Constructor for the initial state of the rope.
     * 
     * @param length length of the rope
     */
    Rope(int length) {
        // TODO take length into consideration
        this(Point.ORIGIN, Point.ORIGIN, Set.of(Point.ORIGIN));
    }
    
    /**
     * Performs a single step in the given direction.
     * 
     * @param direction direction to step to
     * @return updated state of the rope
     */
    Rope step(Direction direction) {
        var newHead = direction.move(head);
        Point newTail;
        if (newHead.neighboursIncludingDiagonals().contains(tail)) {
            newTail = tail;
        } else {
            // Move the tail!
            newTail = new Point(
                    tail.x() + Integer.signum(newHead.x() - tail.x()),
                    tail.y() + Integer.signum(newHead.y() - tail.y()));
        }
        Set<Point> newVisited = new HashSet<>(visited);
        newVisited.add(newTail);
        var result = new Rope(newHead, newTail, newVisited);
        LOGGER.debug("Updated rope after performing a step in direction {}: {}", direction, result);
        return result;
    }
}
