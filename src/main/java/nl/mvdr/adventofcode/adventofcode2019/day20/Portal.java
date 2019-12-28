package nl.mvdr.adventofcode.adventofcode2019.day20;

import java.util.Comparator;
import java.util.Set;

import nl.mvdr.adventofcode.point.Point;

/**
 * A portal connecting two points through teleportation.
 *
 * @author Martijn van de Rijdt
 */
public class Portal {
    /** Label of this portal. */
    private final String name;
    /** The point of this portal on the maze's outside. */
    private final Point outerPoint;
    /** The point of this portal on the maze's inside (the donut hole). */
    private final Point innerPoint;

    /**
     * Creates a new portal connecting the given points.
     * 
     * @param name label of this portal
     * @param points points; should contain exactly two values
     * @param center center of the maze
     * @return portal
     */
    static Portal createPortal(String name, Set<Point> points, Point center) {
        if (points.size() != 2) {
            throw new IllegalArgumentException("A portal must connect exactly two points, but got: " + points);
        }
        
        // TODO Manhattan distance to center is not a correct way to determine which is inner and which is outer.
        // See: point FD in example 2.
        Point inner = points.stream()
                .min(Comparator.comparingInt(point -> point.manhattanDistance(center)))
                .orElseThrow();
        Point outer = points.stream()
                .filter(point -> point != inner)
                .findFirst()
                .orElseThrow();
        
        return new Portal(name, outer, inner);
    }
    
    /**
     * Constructor.
     * 
     * @param name label of this portal
     * @param outer first point
     * @param inner second point
     */
    Portal(String name, Point outer, Point inner) {
        super();
        this.name = name;
        this.outerPoint = outer;
        this.innerPoint = inner;
    }
    
    Point getOuterPoint() {
        return outerPoint;
    }
    
    Point getInnerPoint() {
        return innerPoint;
    }

    @Override
    public String toString() {
        return name + ": " + innerPoint + " -> " + outerPoint;
    }
    
    
}
