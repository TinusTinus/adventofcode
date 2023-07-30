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
        if (0 < position.flowRate() &&!openValves.contains(position)) {
            // We could open this valve. (Note that, if the flow rate is zero, there is no point in opening this valve.)
            Set<Valve> newOpenValves = new HashSet<>(openValves);
            newOpenValves.add(position);
            var newPressureReleased = pressureReleased + position.flowRate() * remainingMinutes;
            result.add(new State(network, position, newOpenValves, remainingMinutes - 1, newPressureReleased));
        }
        // We could move through a tunnel.
        for (Valve tunnelExit : network.tunnels().get(position)) {
            result.add(new State(network, tunnelExit, openValves, remainingMinutes - 1, pressureReleased));
        }
        // We could also just do nothing for a minute, but we may as well keep walking back and forth through tunnels.
        return result;
    }
}
