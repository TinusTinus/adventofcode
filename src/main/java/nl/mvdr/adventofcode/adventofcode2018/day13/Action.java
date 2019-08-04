package nl.mvdr.adventofcode.adventofcode2018.day13;

import java.util.Map;

/**
 * Possible actions a mine cart can take when at an intersection.
 *
 * @author Martijn van de Rijdt
 */
enum Action {
    /** Keep travelling in the same direction. */
    GO_STRAIGHT(Map.of(
            Direction.UP, Direction.UP,
            Direction.DOWN, Direction.DOWN,
            Direction.LEFT, Direction.LEFT,
            Direction.RIGHT, Direction.RIGHT)),
    /** Turn left. */
    TURN_LEFT(Map.of(Direction.UP, Direction.LEFT,
            Direction.DOWN, Direction.RIGHT,
            Direction.LEFT, Direction.DOWN, 
            Direction.RIGHT, Direction.UP)),
    /** Turn right. */
    TURN_RIGHT(Map.of(Direction.UP, Direction.RIGHT, 
            Direction.DOWN, Direction.LEFT, 
            Direction.LEFT, Direction.UP, 
            Direction.RIGHT, Direction.DOWN));
    
    /** Map indicating, given a cart facing a direction, what its next direction should be. */
    private final Map<Direction, Direction> nextDirections;
    
    /**
     * Constructor.
     * 
     * @param nextDirections map indicating, given a cart facing a direction, what its next direction should be
     */
    Action(Map<Direction, Direction> nextDirections) {
        this.nextDirections = nextDirections;
    }
    
    /**
     * Next direction for a cart that is performing this action.
     * 
     * @param currentDirection cart's current direction
     * @return cart's next direction
     */
    Direction getNextDirection(Direction direction) {
        return nextDirections.get(direction);
    }
}
