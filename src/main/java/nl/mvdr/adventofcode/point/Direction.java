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
    UP('^', true, Point::aboveNeighbour),
    /** Down / south. */
    DOWN('v', true, Point::belowNeighbour),
    /** Left / west. */
    LEFT('<', false, Point::leftNeighbour),
    /** Right / east. */
    RIGHT('>', false, Point::rightNeighbour);
    
    /** Character representation of this direction. */
    private final char representation;
    
    /** Whether this direction is vertical. */
    private final boolean vertical;
    
    /** Function that, given a location, determines the next location. */
    private final Function<Point, Point> next;
    
    /**
     * Constructor.
     * 
     * @param representation character representation of this direction
     * @param vertical whether this direction is vertical
     * @param next function that, given a location, determines the next location
     */
    Direction(char representation, boolean vertical, Function<Point, Point> next) {
        this.representation = representation;
        this.vertical = vertical;
        this.next = next;
    }
    
    /**
     * Gives the direction represented by the given character.
     * 
     * @param representation character representation
     * @return optional direction
     */
    public static Optional<Direction> of(char representation) {
        return Stream.of(Direction.values())
                .filter(direction -> direction.representation == representation)
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
        return turnCounterClockwise().turnCounterClockwise();
    }
    
    /** @return the direction clockwise from this one */
    public Direction turnClockwise() {
        return reverse().turnCounterClockwise();
    }
    
    @Override
    public String toString() {
        return "" + representation;
    }
}
