package nl.mvdr.adventofcode.adventofcode2022.day14;

import java.util.HashSet;
import java.util.Set;

import nl.mvdr.adventofcode.point.Direction;
import nl.mvdr.adventofcode.point.Point;

/**
 * A line between two points.
 * 
 * @param start the starting point of the line (inclusive)
 * @param end the ending point of the line (inclusive)
 * @author Martijn van de Rijdt
 */
record Line(Point start, Point end) {
    /**
     * @return the points which are part of this line
     */
    Set<Point> points() {
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
