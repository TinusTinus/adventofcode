package nl.mvdr.adventofcode.adventofcode2022.day16;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;

import javax.annotation.processing.Generated;

/**
 * A valve.
 *
 * @author Martijn van de Rijdt
 */
record Valve(String name, int flowRate) {

    /**
     * Finds the valve with the given name in the given set.
     * 
     * @param valves the valves to search
     * @param name name of the valve to search for
     * @return valve
     */
    static Valve find(Set<Valve> valves, String name) {
        return valves.stream()
                .filter(valve -> name.equals(valve.name()))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("No valve found with name " + name));
    }

    @Override
    @Generated("eclipse")
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    @Generated("eclipse")
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Valve other = (Valve) obj;
        return Objects.equals(name, other.name);
    }
}
