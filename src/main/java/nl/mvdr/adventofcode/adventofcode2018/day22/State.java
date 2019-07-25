package nl.mvdr.adventofcode.adventofcode2018.day22;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    
    /**
     * Possible moves from this state. Each of these takes one minute to complete.
     * 
     * @param cave the cave
     * @return new states
     */
    Set<State> getMoves(Cave cave) {
        return this.location.neighbours().stream()
                .filter(point -> 0 <= point.getX())
                .filter(point -> 0 <= point.getY())
                .filter(point -> cave.getRegionAt(point).getType().getUsableEquipment().contains(equipment))
                .map(point -> new State(point, equipment))
                .collect(Collectors.toSet());
    }
    
    /**
     * Possible equipment changes from this state. Each of these takes seven minutes to complete.
     * 
     * @return new states
     */
    Set<State> getEquipmentChanges() {
        return Stream.of(Equipment.values())
                .filter(value -> value != equipment)
                .map(value -> new State(location, value))
                .collect(Collectors.toSet());
    }
}
