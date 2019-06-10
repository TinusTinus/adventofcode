package nl.mvdr.adventofcode.adventofcode2018.day13;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Representation of a mine cart.
 *
 * @author Martijn van de Rijdt
 */
class MineCart {
    /** Coordinate on the horizontal axis. */
    private final int x;
    
    /** Coordinate on the vertical axis. */
    private final int y;
    
    /** Direction of this cart. */
    private final Direction direction;
    
    /** Cyclical collection of the actions this cart will take whenever it reaches intersections. */
    private final Deque<Action> actions;

    /**
     * Constructor.
     * 
     * @param x coordinate on the horizontal axis
     * @param y coordinate on the vertical axis
     * @param direction direction of this cart
     */
    MineCart(int x, int y, Direction direction) {
        this(x, y, direction, new LinkedList<>(List.of(Action.TURN_LEFT, Action.GO_STRAIGHT, Action.TURN_RIGHT)));
    }
    
    /**
     * Constructor.
     * 
     * @param x coordinate on the horizontal axis
     * @param y coordinate on the vertical axis
     * @param direction direction of this cart
     * @param actions cyclical collection of the actions this cart will take whenever it reaches intersections
     */
    MineCart(int x, int y, Direction direction, Deque<Action> actions) {
        super();
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.actions = actions;
    }
    
    /** @return coordinate on the horizontal axis */
    int getX() {
        return x;
    }
    
    /** @return coordinate on the vertical axis */
    int getY() {
        return y;
    }
    
    /** @return direction of this cart */
    Direction getDirection() {
        return direction;
    }
    
    /** @return cyclical collection of the actions this cart will take whenever it reaches intersections */
    Deque<Action> getActions() {
        return actions;
    }
    
    @Override
    public String toString() {
        return direction.toString();
    }
}
