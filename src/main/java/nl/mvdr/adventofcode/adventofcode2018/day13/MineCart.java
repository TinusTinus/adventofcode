package nl.mvdr.adventofcode.adventofcode2018.day13;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import nl.mvdr.adventofcode.adventofcode2018.point.Point;

/**
 * Representation of a mine cart.
 *
 * @author Martijn van de Rijdt
 */
class MineCart {
    /** Location of the mine cart. */
    private final Point location;
    
    /** Direction of this cart. */
    private final Direction direction;
    
    /** Cyclical collection of the actions this cart will take whenever it reaches intersections. */
    private final Deque<Action> actions;

    /**
     * Constructor.
     * 
     * @param location the cart's location
     * @param direction direction of this cart
     */
    MineCart(Point location, Direction direction) {
        this(location, direction, new LinkedList<>(List.of(Action.TURN_LEFT, Action.GO_STRAIGHT, Action.TURN_RIGHT)));
    }
    
    /**
     * Constructor.
     * 
     * @param x coordinate on the horizontal axis
     * @param y coordinate on the vertical axis
     * @param direction direction of this cart
     * @param actions cyclical collection of the actions this cart will take whenever it reaches intersections
     * @deprecated use {@link #MineCart(Point, Direction, Deque)} instead
     */
    @Deprecated
    MineCart(int x, int y, Direction direction, Deque<Action> actions) {
        this(new Point(x, y), direction, actions);
    }
    
    /**
     * Constructor.
     * 
     * @param location the cart's location
     * @param direction direction of this cart
     * @param actions cyclical collection of the actions this cart will take whenever it reaches intersections
     */
    MineCart(Point location, Direction direction, Deque<Action> actions) {
        super();
        this.location = location;
        this.direction = direction;
        this.actions = actions;
    }
    
    /** @return the mine cart's current location */
    Point getLocation() {
        return location;
    }
    
    
    /** @return coordinate on the horizontal axis */
    @Deprecated
    int getX() {
        return location.getX();
    }
    
    /** @return coordinate on the vertical axis */
    @Deprecated
    int getY() {
        return location.getY();
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
