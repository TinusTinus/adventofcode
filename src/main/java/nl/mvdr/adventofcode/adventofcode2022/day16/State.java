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
                network.graph()
                    .vertexSet()
                    .stream()
                    .filter(valve -> valve.flowRate() == 0)
                    .collect(Collectors.toSet()),
                30,
                0);
    }
    
    /**
     * @return possible actions to take
     */
    Set<State> nextStates() {
        Set<State> result = new HashSet<>();
        var newRemainingMinutes = remainingMinutes - 1;
        if (!openValves.contains(position)) {
            Set<Valve> newOpenValves = new HashSet<>(openValves);
            newOpenValves.add(position);
            
            var newPressureReleased = pressureReleased + position.flowRate() * newRemainingMinutes;
            result.add(new State(network, position, newOpenValves, newRemainingMinutes, newPressureReleased));
        }
        // We could move through a tunnel.
        for (Valve valve : network.graph().vertexSet()) {
            if (network.graph().containsEdge(position, valve)) {
                result.add(new State(network, valve, openValves, newRemainingMinutes, pressureReleased));
            }
        }
        if (result.isEmpty()) {
            // Nothing to do. We could also just do nothing for a minute.
            result.add(new State(network, position, openValves, newRemainingMinutes, pressureReleased));
        }
        
        return result;
    }
}
