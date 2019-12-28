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
    /** The point of this portal on the maze's outside. */
    private final Point outerPoint;
    /** The point of this portal on the maze's inside (the donut hole). */
    private final Point innerPoint;

    /**
     * Creates a new portal connecting the given points.
     * 
     * @param points points; should contain exactly two values
     * @param center center of the maze
     * @return portal
     */
    static Portal createPortal(Set<Point> points, Point center) {
        if (points.size() != 2) {
            throw new IllegalArgumentException("A portal must connect exactly two points, but got: " + points);
        }
        
        Point inner = points.stream()
                .min(Comparator.comparingInt(point -> point.manhattanDistance(center)))
                .orElseThrow();
        Point outer = points.stream()
                .filter(point -> point != inner)
                .findFirst()
                .orElseThrow();
        
        return new Portal(outer, inner);
    }
    
    /**
     * Constructor.
     * 
     * @param outer first point
     * @param inner second point
     */
    Portal(Point outer, Point inner) {
        super();
        this.outerPoint = outer;
        this.innerPoint = inner;
    }
    
    Point getOuterPoint() {
        return outerPoint;
    }
    
    Point getInnerPoint() {
        return innerPoint;
    }
}
