package nl.mvdr.adventofcode.adventofcode2022.day16;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * A possible state of a network.
 *
 * @param network the network
 * @param position current position in the network
 * @param closedValves valves which can still be opened to release pressure
 * @param remainingMinutes the remaining minutes until the vulcano erupts
 * @param pressureReleased amount of pressure released, as a result of the valves that have already been opened
 * @param currentPath the path we are currently on to get to a specific closed valve; may be null, indicating that we are not (yet) on a path
 * @author Martijn van de Rijdt
 */
record State(Network network, Valve position, Set<Valve> closedValves, int remainingMinutes, int pressureReleased, List<Valve> currentPath) {
    
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
                    .filter(valve -> valve.flowRate() != 0)
                    .collect(Collectors.toSet()),
                30,
                0,
                null);
    }
    
    /**
     * @return possible actions to take
     */
    Set<State> nextStates() {
        Set<State> result = new HashSet<>();
        var newRemainingMinutes = remainingMinutes - 1;
        
        if (currentPath == null) {
            // TODO figure out new paths
            if (closedValves.contains(position)) {
                Set<Valve> newClosedValves = new HashSet<>(closedValves);
                newClosedValves.remove(position);
                
                var newPressureReleased = pressureReleased + position.flowRate() * newRemainingMinutes;
                result.add(new State(network, position, newClosedValves, newRemainingMinutes, newPressureReleased, null));
            }
            // We could move through a tunnel.
            for (Valve valve : network.graph().vertexSet()) {
                if (network.graph().containsEdge(position, valve)) {
                    result.add(new State(network, valve, closedValves, newRemainingMinutes, pressureReleased, null));
                }
            }
        } else if (currentPath.isEmpty()) {
            // Close this valve.
            Set<Valve> newClosedValves = new HashSet<>(closedValves);
            newClosedValves.remove(position);
            var newPressureReleased = pressureReleased + position.flowRate() * newRemainingMinutes;
            result.add(new State(network, position, newClosedValves, newRemainingMinutes, newPressureReleased, null));
        } else {
            // Move through the tunnel.
            result.add(new State(network, currentPath.get(0), closedValves, newRemainingMinutes, pressureReleased, currentPath.subList(1, currentPath.size())));
        }
        
        if (result.isEmpty()) {
            // Nothing to do. Let's just do nothing for a minute.
            result.add(new State(network, position, closedValves, newRemainingMinutes, pressureReleased, null));
        }
        return result;
    }
}
