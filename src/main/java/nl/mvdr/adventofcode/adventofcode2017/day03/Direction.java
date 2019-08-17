package nl.mvdr.adventofcode.adventofcode2017.day03;

import java.util.function.Function;

import nl.mvdr.adventofcode.point.Point;

enum Direction {
    /** Right. */
    RIGHT(Point::rightNeighbour),
    /** Up.*/
    UP(Point::aboveNeighbour),
    /** Left. */
    LEFT(Point::leftNeighbour),
    /** Down. */
    DOWN(Point::belowNeighbour);
    
    private final Function<Point, Point> next;
    
    Direction(Function<Point, Point> next) {
        this.next = next;
    }
    
    /**
     * Moves a single step in this direction.
     * 
     * @param point start location
     * @return next location
     */
    Point move(Point point) {
        return next.apply(point);
    }
    
    /**
     * Gets the direction counter-clockwise from this one.
     * 
     * @return direction
     */
    Direction turnCouterClockwise() {
        Direction result;
        if (this == RIGHT) {
            result = UP;
        } else if (this == UP) {
            result = LEFT;
        } else if (this == LEFT) {
            result = DOWN;
        } else if (this == DOWN) {
            result = RIGHT;
        } else {
            throw new IllegalStateException("Unexpected direction: " + this);
        }
        return result;
    }
}
