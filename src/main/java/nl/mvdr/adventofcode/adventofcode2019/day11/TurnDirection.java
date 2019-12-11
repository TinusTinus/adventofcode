package nl.mvdr.adventofcode.adventofcode2019.day11;

import java.util.function.Function;
import java.util.stream.Stream;

import nl.mvdr.adventofcode.point.Direction;

/**
 * A direction the robot can turn to.
 *
 * @author Martijn van de Rijdt
 */
enum TurnDirection {
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
    static TurnDirection of(long code) {
        return Stream.of(values())
                .filter(value -> value.code == code)
                .findFirst()
                .orElseThrow();
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
    Direction turn(Direction startingDirection) {
        return turnFunction.apply(startingDirection);
    }
}
