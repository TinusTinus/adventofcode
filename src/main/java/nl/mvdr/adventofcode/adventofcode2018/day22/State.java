package nl.mvdr.adventofcode.adventofcode2018.day22;

import java.util.Objects;

import javax.annotation.processing.Generated;

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

    @Override
    @Generated("Eclipse")
    public int hashCode() {
        return Objects.hash(equipment, location);
    }

    @Override
    @Generated("Eclipse")
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        State other = (State) obj;
        return equipment == other.equipment && Objects.equals(location, other.location);
    }
    
    
}
