package nl.mvdr.adventofcode.adventofcode2022.day16;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.jgrapht.alg.interfaces.ShortestPathAlgorithm;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultEdge;

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
            ShortestPathAlgorithm<Valve, DefaultEdge> algorithm = new DijkstraShortestPath<>(network.graph());
            var paths = algorithm.getPaths(position);
            for (Valve closedValve : closedValves) {
                var graphPath = paths.getPath(closedValve);
                if (graphPath.getLength() < remainingMinutes) {
                    var path = graphPath.getVertexList();
                    if (path.get(0) != position) {
                        throw new IllegalStateException();
                    }
                    result.add(new State(network, path.get(1), closedValves, newRemainingMinutes, pressureReleased, path.subList(2, path.size())));
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
