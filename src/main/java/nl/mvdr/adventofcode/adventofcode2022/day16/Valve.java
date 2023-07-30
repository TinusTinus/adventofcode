package nl.mvdr.adventofcode.adventofcode2022.day16;

import java.util.NoSuchElementException;
import java.util.Set;

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
}
