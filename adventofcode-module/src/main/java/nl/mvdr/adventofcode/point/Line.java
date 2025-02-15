package nl.mvdr.adventofcode.point;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

/**
 * A line between two points.
 * 
 * @param start the starting point of the line (inclusive)
 * @param end the ending point of the line (inclusive)
 * @author Martijn van de Rijdt
 */
public record Line(Point start, Point end) {
    
    private static final String SEPARATOR = " -> ";

    /**
     * Parses a textual representation of a line.
     * 
     * @param text textual representation of a line, for example: "6,4 -> 2,0"
     * @return the line represented by the given text
     */
    public static Line parse(String text) {
        var points = Stream.of(text.split(SEPARATOR))
                .map(Point::parse)
                .toList();
        
        if (points.size() != 2) {
            throw new IllegalArgumentException("Unable to parse as a line: " + text);
        }
        
        return new Line(points.getFirst(), points.getLast());
    }
    
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
     * @return whether this is a horizontal line
     */
    private boolean isHorizontal() {
        return start.y() == end.y();
    }

    /**
     * @return whether this is a vertical line
     */
    private boolean isVertical() {
        return start.x() == end.x();
    }
    
    /**
     * @return whether this is a horizontal or vertical line
     */
    public boolean isHorizontalOrVertical() {
        return isHorizontal() || isVertical();
    }
    
    /**
     * @return direction of this line
     */
    private Direction determineDirection() {
        Direction result;
        if (isVertical()) {
            if (start.y() < end.y()) {
                result = Direction.DOWN;
            } else if (end.y() < start.y()){
                result = Direction.UP;
            } else {
                throw new IllegalStateException("Unable to determine direction for vertical line: " + this);
            }
        } else if (isHorizontal()) {
            if (start.x() < end.x()) {
                result = Direction.RIGHT;
            } else if (end.x() < start.x()) {
                result = Direction.LEFT;
            } else {
                // Should be unreachable
                throw new IllegalStateException("Unable to determine direction for horizontal line: " + this);
            }
        } else if (start.x() < end.x()) {
            // Diagonal to the right
            var diffX = end.x() - start.x();
            if (start.y() + diffX == end.y()) {
                result = Direction.DOWN_RIGHT;
            } else if (start.y() - diffX == end.y()) {
                result = Direction.UP_RIGHT;
            } else {
                // Not a 45 degree angle
                throw new IllegalStateException("Unable to determine direction for diagonal line: " + this);
            }
        } else {
            // Diagonal to the left
            var diffX = start.x() - end.x();
            if (start.y() + diffX == end.y()) {
                result = Direction.DOWN_LEFT;
            } else if (start.y() - diffX == end.y()) {
                result = Direction.UP_LEFT;
            } else {
                // Not a 45 degree angle
                throw new IllegalStateException("Unable to determine direction for diagonal line: " + this);
            }
        }
        
        return result;
    }

    @Override
    public String toString() {
        return start + SEPARATOR + end;
    }
}
