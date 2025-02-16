package nl.mvdr.adventofcode.adventofcode2023.day23;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import nl.mvdr.adventofcode.point.Point;

/**
 * A point of interest on the map of hiking trails.
 * 
 * Points of interest are: the start, the goal, and intersections;
 * where an intersection is defined as a location that can be exited
 * in more than two different directions.
 *
 * @param point the location
 * @param pathLengths a collection containing as its keys the other points of interest which can be reached from this one,
 *     via a direct path which does not cross any other points of interest, and as its values the length of the path
 * @author Martijn van de Rijdt
 */
record PointOfInterest(Point point, Map<PointOfInterest, Integer> pathLengths) {

    /**
     * Convenience constructor, which initializes the map of path lengths as a mutable set, but does not popupate it.
     * 
     * @param point the point
     */
    PointOfInterest(Point point) {
        this(point, new HashMap<>());
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(point);
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj 
                || (getClass() == obj.getClass()
                    && Objects.equals(this.point, ((PointOfInterest)obj).point));
    }
    
    @Override
    public String toString() {
        return point.toString();
    }
}
