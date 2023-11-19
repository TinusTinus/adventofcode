package nl.mvdr.adventofcode.point;

import java.util.Optional;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * A direction in two dimension.
 *
 * @author Martijn van de Rijdt
 */
public enum Direction {
    /** Up / north. */
    UP(true, Point::aboveNeighbour, 1L, 3, '^', 'U', 'N'),
    /** Down / south. */
    DOWN(true, Point::belowNeighbour, 2L, 1, 'v', 'D', 'S'),
    /** Left / west. */
    LEFT(false, Point::leftNeighbour, 3L, 2, '<', 'L', 'W'),
    /** Right / east. */
    RIGHT(false, Point::rightNeighbour, 4L, 0, '>', 'R', 'E');
    
    /** Whether this direction is vertical. */
    private final boolean vertical;
    
    /** Function that, given a location, determines the next location. */
    private final Function<Point, Point> next;
    
    /** Intcode representation of this direction. */
    private final long code;
    
    /** Value of this direction when representing a facing in a password. */
    private final int passwordValue;
    
    /** Character representations of this direction. */
    private final char[] representations;
    
    /**
     * Constructor.
     * 
     * @param vertical whether this direction is vertical
     * @param next function that, given a location, determines the next location if a single step is taken in this direction
     * @param code Intcode representation of this direction
     * @param passwordValue value of this direction when representing a facing in a password
     * @param representations character representations of this direction
     */
    Direction(boolean vertical, Function<Point, Point> next, long code, int passwordValue, char... representations) {
        this.vertical = vertical;
        this.next = next;
        this.code = code;
        this.passwordValue = passwordValue;
        this.representations = representations;
    }
    
    /**
     * Gives the direction represented by the given character.
     * 
     * @param representation character representation
     * @return optional direction
     */
    public static Optional<Direction> of(char representation) {
        return Stream.of(Direction.values())
                .filter(direction -> IntStream.range(0, direction.representations.length).map(i -> direction.representations[i]).anyMatch(c -> c == representation))
                .findFirst();
    }
    
    /**
     * Gives the direction represented by the given string.
     * 
     * @param representation string representation
     * @return direction
     */
    public static Direction parse(String representation) {
        if (representation.length() != 1) {
            throw new IllegalArgumentException("Direction representation must be a single character but was: " + representation);
        }
        return of(representation.charAt(0)).orElseThrow(() -> new IllegalArgumentException("Unknown direction: " + representation));
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
    
    /**
     * Moves in this direction.
     * 
     * @param location current location
     * @param numberOfSteps number of steps to move
     * @return next location
     */
    public Point move(Point location, int numberOfSteps) {
        // Note: this could be done a lot more efficiently, but that has not been a priority up until now.
        // Let's just reuse the existing move method.
        Point result;
        if (numberOfSteps < 0) {
            result = reverse().move(location, -numberOfSteps);
        } else {
            result = location;
            for (int i = 0; i != numberOfSteps; i++) {
                result = move(result);
            }
        }
        return result;
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

    /**
     * Value of this direction when representing a facing in a password.
     * 
     * 0 for right (>), 1 for down (v), 2 for left (<), and 3 for up (^)
     * 
     * @return facing value
     */
    public int getPasswordValue() {
        return passwordValue;
    }
    
    @Override
    public String toString() {
        return "" + representations[0];
    }
}
