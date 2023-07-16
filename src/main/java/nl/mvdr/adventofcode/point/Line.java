package nl.mvdr.adventofcode.point;

import java.util.HashSet;
import java.util.Set;

/**
 * A line between two points.
 * 
 * @param start the starting point of the line (inclusive)
 * @param end the ending point of the line (inclusive)
 * @author Martijn van de Rijdt
 */
public record Line(Point start, Point end) {
    /**
     * Checks whether the given three points are all on the same line.
     * 
     * @param point1 first point
     * @param point2 second point
     * @param point3 third point
     * @return whether the three points are all on the same line
     */
    public static boolean sameLine(Point point1, Point point2, Point point3) {
        int x1 = point1.x();
        int y1 = point1.y();
        int x2 = point2.x();
        int y2 = point2.y();
        int x3 = point3.x();
        int y3 = point3.y();
        
        return (x2 - x1) * (y3 - y1) == (y2 - y1) * (x3 - x1);
    }
    
    /**
     * @return the points which are part of this line
     */
    public Set<Point> points() {
        var direction = determineDirection();
        Set<Point> result = new HashSet<>();
        Point point = start;
        while (!point.equals(end)) {
            result.add(point);
            point = direction.move(point);
        }
        result.add(end);
        return result;
    }
    
    /**
     * @return direction of this line
     */
    private Direction determineDirection() {
        Direction result;
        if (start.x() == end.x()) {
            if (start.y() < end.y()) {
                result = Direction.DOWN;
            } else if (end.y() < start.y()){
                result = Direction.UP;
            } else {
                throw new IllegalStateException("Unable to determine direction for vertical line: " + this);
            }
        } else if (start.y() == end.y()) {
            if (start.x() < end.x()) {
                result = Direction.RIGHT;
            } else if (end.x() < start.x()) {
                result = Direction.LEFT;
            } else {
                // Should be unreachable
                throw new IllegalStateException("Unable to determine direction for horizontal line: " + this);
            }
        } else {
            throw new IllegalStateException("Unable to determine direction for diagonal line: " + this);
        }
        
        return result;
    }

    @Override
    public String toString() {
        return start + " -> " + end;
    }
}
