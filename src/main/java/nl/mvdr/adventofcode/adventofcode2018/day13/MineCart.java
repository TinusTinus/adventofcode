package nl.mvdr.adventofcode.adventofcode2018.day13;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import nl.mvdr.adventofcode.point.Point;

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
    
    /** @return direction of this cart */
    Direction getDirection() {
        return direction;
    }
    
    /** @return cyclical collection of the actions this cart will take whenever it reaches intersections */
    Deque<Action> getActions() {
        return actions;
    }
    
    /** @return next location of this cart, if it continues moving in its current direction */
    Point nextLocation() {
        return direction.nextLocation(location);
    }
    
    @Override
    public String toString() {
        return direction.toString();
    }
}
