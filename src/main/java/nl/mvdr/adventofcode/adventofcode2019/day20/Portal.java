package nl.mvdr.adventofcode.adventofcode2019.day20;

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
