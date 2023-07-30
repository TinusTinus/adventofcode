package nl.mvdr.adventofcode.adventofcode2022.day16;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * A possible state of the network.
 *
 * @author Martijn van de Rijdt
 */
record State(Network network, Valve position, Set<Valve> openValves, int remainingMinutes, int pressureReleased) {
    
    /**
     * Constructor, for the initial state of a network.
     * 
     * @param network the network
     */
    State(Network network) {
        
        this(network,
                network.startingPoint(),
                // Consider valves with air pressure = 0 as already open. Opening them has no effect anyway.
                network.tunnels()
                    .keySet()
                    .stream()
                    .filter(valve -> valve.flowRate() == 0)
                    .collect(Collectors.toSet()),
                30,
                0);
    }
    
    /**
     * @return possible states that can be reached from this one
     */
    Set<State> nextStates() {
        Set<State> result = new HashSet<>();
        var newRemainingMinutes = remainingMinutes - 1;
        if (!openValves.contains(position)) {
            // We could open this valve. (Note that, if the flow rate is zero, there is no point in opening this valve.)
            Set<Valve> newOpenValves = new HashSet<>(openValves);
            newOpenValves.add(position);
            
            var newPressureReleased = pressureReleased + position.flowRate() * newRemainingMinutes;
            result.add(new State(network, position, newOpenValves, newRemainingMinutes, newPressureReleased));
        }
        // We could move through a tunnel.
        for (Valve tunnelExit : network.tunnels().get(position)) {
            result.add(new State(network, tunnelExit, openValves, newRemainingMinutes, pressureReleased));
        }
        // We could also just do nothing for a minute, but we may as well keep walking back and forth through tunnels.
        return result;
    }
}
