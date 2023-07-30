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
 * @param closedValves valves which can still be opened to release pressure
 * @param remainingMinutes the remaining minutes until the vulcano erupts
 * @param pressureReleased amount of pressure released, as a result of the valves that have already been opened
 * @author Martijn van de Rijdt
 */
record State(Network network, Set<Valve> closedValves, int remainingMinutes, int pressureReleased, Actor actor) {
    
    /**
     * Constructor, for the initial state of a network.
     * 
     * @param network the network
     */
    State(Network network) {
        this(network,
                // Consider valves with air pressure = 0 as already open. Opening them has no effect anyway.
                network.graph()
                    .vertexSet()
                    .stream()
                    .filter(valve -> 0 < valve.flowRate())
                    .collect(Collectors.toSet()),
                30,
                0,
                new Actor(network.startingPoint(), null));
    }
    
    /**
     * @return possible actions to take
     */
    Set<State> nextStates() {
        var newRemainingMinutes = remainingMinutes - 1;
        var newClosedValves = closedValves;
        var newPressureReleased = pressureReleased;
        Set<Actor> newActors = new HashSet<>();
        
        if (actor.currentPath() == null) {
            // Figure out which valves the actor could move to next.
            ShortestPathAlgorithm<Valve, DefaultEdge> algorithm = new DijkstraShortestPath<>(network.graph());
            var paths = algorithm.getPaths(actor.currentPosition());
            for (Valve closedValve : closedValves) {
                var graphPath = paths.getPath(closedValve);
                if (graphPath.getLength() < remainingMinutes) { // Only consider valves which we could get to in time.
                    var path = graphPath.getVertexList();
                    newActors.add(new Actor(path.get(1), path.subList(2, path.size())));
                }
            }
            if (newActors.isEmpty()) {
                // Nowhere to go. The actor stays here.
                newActors = Set.of(actor);
            }
        } else if (actor.currentPath().isEmpty()) {
            // The actor has reached their destination and can now close the valve.
            newClosedValves = new HashSet<>(newClosedValves);
            if (newClosedValves.remove(actor.currentPosition())) { // Make sure we do not count a valve twice, if both actors happen to have arrived in the same place.
                newPressureReleased = newPressureReleased + actor.currentPosition().flowRate() * newRemainingMinutes;
            }
            newActors.add(new Actor(actor.currentPosition(), null));
        } else {
            // Move along the path.
            Valve newPosition = actor.currentPath().get(0);
            List<Valve> newPath = actor.currentPath().subList(1, actor.currentPath().size());
            newActors.add(new Actor(newPosition, newPath));
        }
        
        Set<State> result = new HashSet<>();
        for (Actor newActor : newActors) {
            result.add(new State(network, newClosedValves, newRemainingMinutes, newPressureReleased, newActor));
        }
        return result;
    }
}
