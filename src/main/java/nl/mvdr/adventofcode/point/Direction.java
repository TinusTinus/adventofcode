package nl.mvdr.adventofcode.point;

import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * A direction in two dimension.
 *
 * @author Martijn van de Rijdt
 */
public enum Direction {
    /** Up / north. */
    UP('^', true, Point::aboveNeighbour, 1L),
    /** Down / south. */
    DOWN('v', true, Point::belowNeighbour, 2L),
    /** Left / west. */
    LEFT('<', false, Point::leftNeighbour, 3L),
    /** Right / east. */
    RIGHT('>', false, Point::rightNeighbour, 4L);
    
    /** Character representation of this direction. */
    private final char representation;
    
    /** Whether this direction is vertical. */
    private final boolean vertical;
    
    /** Function that, given a location, determines the next location. */
    private final Function<Point, Point> next;
    
    /** Intcode representation of this direction. */
    private final long code;
    
    /**
     * Constructor.
     * 
     * @param representation character representation of this direction
     * @param vertical whether this direction is vertical
     * @param next function that, given a location, determines the next location
     * @param code Intcode representation of this direction
     */
    Direction(char representation, boolean vertical, Function<Point, Point> next, long code) {
        this.representation = representation;
        this.vertical = vertical;
        this.next = next;
        this.code = code;
    }
    
    /**
     * Gives the direction represented by the given character.
     * 
     * @param representation character representation
     * @return optional direction
     */
    public static Optional<Direction> of(char representation) {
        return Stream.of(Direction.values())
                .filter(direction -> direction.representation == representation || direction.name().charAt(0) == representation)
                .findFirst();
    }
    
    public boolean isVertical() {
        return vertical;
    }
    
    /**
     * Moves a single step in this direction.
     * 
     * @param location current location
     * @return next location
     */
    public Point move(Point location) {
        return next.apply(location);
    }
    
    /** @return the direction counter-clockwise from this one */
    public Direction turnCounterClockwise() {
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
    
    /** @return the direction opposite this one */
    public Direction reverse() {
        Direction result;
        if (this == RIGHT) {
            result = LEFT;
        } else if (this == UP) {
            result = DOWN;
        } else if (this == LEFT) {
            result = RIGHT;
        } else if (this == DOWN) {
            result = UP;
        } else {
            throw new IllegalStateException("Unexpected direction: " + this);
        }
        return result;
    }
    
    /** @return the direction clockwise from this one */
    public Direction turnClockwise() {
        Direction result;
        if (this == RIGHT) {
            result = DOWN;
        } else if (this == UP) {
            result = RIGHT;
        } else if (this == LEFT) {
            result = UP;
        } else if (this == DOWN) {
            result = LEFT;
        } else {
            throw new IllegalStateException("Unexpected direction: " + this);
        }
        return result;
    }
    
    /** @return Intcode representation of this direction */
    public long getCode() {
        return code;
    }
    
    @Override
    public String toString() {
        return "" + representation;
    }
}
