package nl.mvdr.adventofcode.adventofcode2018.day22;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

import javax.annotation.processing.Generated;

import nl.mvdr.adventofcode.point.Point;

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
     * Creates the initial state.
     *
     * "You start at 0,0 (the mouth of the cave) with the torch equipped."
     */
    static State initialState() {
        return new State(Cave.MOUTH, Equipment.TORCH);
    }
    
    /**
     * Creates the target state.
     *
     * "Finally, once you reach the target, you need the torch equipped before you can find him in the dark."
     */
    static State targetState(Cave cave) {
        return new State(cave.getTarget(), Equipment.TORCH);
    }
    
    /**
     * Constructor.
     * 
     * @param location location
     * @param equipment equipment
     */
    private State(Point location, Equipment equipment) {
        super();
        this.location = location;
        this.equipment = equipment;
    }
    
    /** @return valid state transitions from this state */
    Set<Transition> getTransitions(Cave cave) {
        Set<Transition> result = new HashSet<>();
        
        this.location.neighbours().stream()
                .filter(point -> 0 <= point.x())
                .filter(point -> 0 <= point.y())
                .filter(point -> cave.getRegionAt(point).getType().getUsableEquipment().contains(equipment))
                .map(point -> new State(point, equipment))
                .map(nextState -> new Transition(this, nextState, 1))
                .forEach(result::add);
        
        Stream.of(Equipment.values())
                .filter(value -> value != equipment)
                .filter(value -> cave.getRegionAt(location).getType().getUsableEquipment().contains(value))
                .map(value -> new State(location, value))
                .map(nextState -> new Transition(this, nextState, 7))
                .forEach(result::add);
        
        return result;
    }
    
    Point getLocation() {
        return location;
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
