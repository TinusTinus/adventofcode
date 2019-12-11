package nl.mvdr.adventofcode.point;

import java.util.function.Function;
import java.util.stream.Stream;

/**
 * A turn direction.
 *
 * @author Martijn van de Rijdt
 */
public enum TurnDirection {
    /** Turn left 90 degrees. */
    LEFT(0L, Direction::turnCounterClockwise),
    /** Turn right 90 degrees. */
    RIGHT(1L, Direction::turnClockwise);
    
    private final long code;
    private final Function<Direction, Direction> turnFunction;
    
    /**
     * Converts a code to a turn direction.
     * 
     * @param code representation of a turn direction in an Intcode program
     * @return turn direction
     */
    public static TurnDirection of(long code) {
        return Stream.of(values())
                .filter(value -> value.code == code)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unexpected code: " + code));
    }
    
    /**
     * Parses a string representation of a turn direction.
     * 
     * @param c representation of a turn direction: 'L' or 'R'
     * @return turn direction
     */
    public static TurnDirection parse(char c) {
        return Stream.of(values())
                .filter(value -> value.toString().charAt(0) == c)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unexpected turn direction: " + c));
    }
    
    /**
     * Constructor.
     * 
     * @param code representation of a turn direction in an Intcode program
     * @param turnFunction function for actually performing the turn
     */
    TurnDirection(long code, Function<Direction, Direction> turnFunction) {
        this.code = code;
        this.turnFunction = turnFunction;
    }
    
    /**
     * Performs a turn.
     * 
     * @param startingDirection starting direction
     * @return direction after turning
     */
    public Direction turn(Direction startingDirection) {
        return turnFunction.apply(startingDirection);
    }
}
