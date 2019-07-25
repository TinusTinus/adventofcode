package nl.mvdr.adventofcode.adventofcode2018.day22;

import nl.mvdr.adventofcode.adventofcode2018.point.Point;

/**
 * Current state, consisting of a location in the cave and current equipment.
 * 
 * @author Martijn van de Rijdt
 */
class State {
    /** Current location within the cave. */
    private final Point location;
    /** Currently equipped tool. */
    private final Equipment equipment;
    
    /**
     * Constructor for the initial state.
     *
     * "You start at 0,0 (the mouth of the cave) with the torch equipped."
     */
    State() {
        this(Cave.MOUTH, Equipment.TORCH);
    }
    
    /**
     * Constructor.
     * 
     * @param location location
     * @param equipment equipment
     */
    State(Point location, Equipment equipment) {
        super();
        this.location = location;
        this.equipment = equipment;
    }

    @Override
    public String toString() {
        return "State [location=" + location + ", equipment=" + equipment + "]";
    }
}
