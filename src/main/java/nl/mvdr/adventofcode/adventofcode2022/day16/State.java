package nl.mvdr.adventofcode.adventofcode2022.day16;

import java.util.HashSet;
import java.util.Set;

/**
 * A possible state of the network.
 *
 * @author Martijn van de Rijdt
 */
public record State(Network network, Valve position, Set<Valve> openValves, int remainingMinutes, int pressureReleased) {
    
    /**
     * @return possible states that can be reached from this one
     */
    Set<State> nextStates() {
        Set<State> result = new HashSet<>();
        if (!openValves.contains(position)) {
            // We could open this valve.
            Set<Valve> newOpenValves = new HashSet<>(openValves);
            newOpenValves.add(position);
            var newPressureReleased = pressureReleased + position.flowRate() * remainingMinutes;
            result.add(new State(network, position, newOpenValves, remainingMinutes - 1, newPressureReleased));
        }
        // We could move through a tunnel.
        for (Valve tunnelExit : network.tunnels().get(position)) {
            result.add(new State(network, tunnelExit, openValves, remainingMinutes - 1, pressureReleased));
        }
        // We could also just do nothing for a minute.
        result.add(new State(network, position, openValves, remainingMinutes - 1, pressureReleased));
        return result;
    }
}
