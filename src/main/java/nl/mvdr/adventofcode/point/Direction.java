package nl.mvdr.adventofcode.point;

import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * A direction in two dimension.
 *
 * @author Martijn van de Rijdt
 */
public enum Direction {
    /** Up / north. */
    UP(true, 1L, 3, '^', 'U', 'N'),
    /** Down / south. */
    DOWN(true, 2L, 1, 'v', 'D', 'S'),
    /** Left / west. */
    LEFT(false, 3L, 2, '<', 'L', 'W'),
    /** Right / east. */
    RIGHT(false, 4L, 0, '>', 'R', 'E'),
    /** Up-left 45 degree diagonal. */
    UP_LEFT(false, -1L, -1),
    /** Up-right 45 degree diagonal. */
    UP_RIGHT(false, -1L, -1),
    /** Down-left 45 degree diagonal. */
    DOWN_LEFT(false, -1L, -1),
    /** Down-right 45 degree diagonal. */
    DOWN_RIGHT(false, -1L, -1);
    
    /** Whether this direction is vertical. */
    private final boolean vertical;
    
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
     * @param code Intcode representation of this direction; -1 if not applicable
     * @param passwordValue value of this direction when representing a facing in a password; -1 if not applicable
     * @param representations character representations of this direction
     */
    Direction(boolean vertical, long code, int passwordValue, char... representations) {
        this.vertical = vertical;
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
        return move(location, 1);
    }
    
    /**
     * Moves in this direction.
     * 
     * @param location current location
     * @param numberOfSteps number of steps to move
     * @return next location
     */
    public Point move(Point location, int numberOfSteps) {
        return switch(this) {
            case UP -> new Point(location.x(), Math.subtractExact(location.y(), numberOfSteps));
            case DOWN -> new Point(location.x(), Math.addExact(location.y(), numberOfSteps));
            case LEFT -> new Point(Math.subtractExact(location.x(), numberOfSteps), location.y());
            case RIGHT -> new Point(Math.addExact(location.x(), numberOfSteps), location.y());
            case DOWN_LEFT -> new Point(Math.subtractExact(location.x(), numberOfSteps), Math.addExact(location.y(), numberOfSteps));
            case DOWN_RIGHT -> new Point(Math.addExact(location.x(), numberOfSteps), Math.addExact(location.y(), numberOfSteps));
            case UP_LEFT -> new Point(Math.subtractExact(location.x(), numberOfSteps), Math.subtractExact(location.y(), numberOfSteps));
            case UP_RIGHT -> new Point(Math.addExact(location.x(), numberOfSteps), Math.subtractExact(location.y(), numberOfSteps));
            default -> throw new IllegalStateException("Unexpected direction: " + this);
        };
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
