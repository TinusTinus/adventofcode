package nl.mvdr.adventofcode.adventofcode2019.day20;

import nl.mvdr.adventofcode.point.Point;

/**
 * A portal connecting two points through teleportation.
 *
 * @author Martijn van de Rijdt
 */
public class Portal {
    // The two points connected by this portal.
    // Note that the order of these points should not matter.
    private final Point point0;
    private final Point point1;

    /**
     * Constructor.
     * 
     * @param point0 first point
     * @param point1 second point
     */
    Portal(Point point0, Point point1) {
        super();
        this.point0 = point0;
        this.point1 = point1;
    }
    
    Point getPoint0() {
        return point0;
    }
    
    Point getPoint1() {
        return point1;
    }
}
