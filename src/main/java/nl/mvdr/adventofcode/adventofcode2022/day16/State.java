package nl.mvdr.adventofcode.adventofcode2022.day16;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A possible state of a network.
 *
 * @param network the network
 * @param closedValves valves which can still be opened to release pressure
 * @param remainingMinutes the remaining minutes until the vulcano erupts
 * @param pressureReleased amount of pressure released, as a result of the valves that have already been opened
 * @param me current position of the player character
 * @param elephant current position of the helper elephant; null if not applicable
 * @author Martijn van de Rijdt
 */
record State(Network network, Set<Valve> closedValves, int remainingMinutes, int pressureReleased, Actor me, Actor elephant) {
    
    /**
     * Constructor, for the initial state of a network.
     * 
     * @param network the network
     * @param helperElephant whether an elephant can help out
     */
    State(Network network, boolean helperElephant) {
        this(network,
                // Consider valves with air pressure = 0 as already open. Opening them has no effect anyway.
                network.valves()
                    .stream()
                    .filter(valve -> 0 < valve.flowRate())
                    .collect(Collectors.toSet()),
                helperElephant ? 26 : 30,
                0,
                new Actor(network.startingPoint(), null),
                helperElephant ? new Actor(network.startingPoint(), null) : null);
    }
    
    /**
     * @return maximum amount of pressure that can be released starting from this state
     */
    int maxPressureReleased() {
        int result;
        if (remainingMinutes == 0) {
            result = pressureReleased;
        } else {
            // TODO see if we can filter the states to investigate
            result = nextStates().stream()
                    .mapToInt(State::maxPressureReleased)
                    .max()
                    .orElseThrow();
        }
        return result;
    }
    
    /**
     * @return possible actions to take
     */
    private Set<State> nextStates() {
        var newRemainingMinutes = remainingMinutes - 1;
        
        var newClosedValves = closedValves;
        var newPressureReleased = pressureReleased;
        for (Actor actor : getActors()) {
            if (actor.currentPath() != null && actor.currentPath().isEmpty()) {
                // The actor has reached their destination and can now close the valve.
                newClosedValves = new HashSet<>(newClosedValves);
                if (newClosedValves.remove(actor.currentPosition())) { // Make sure we do not count a valve twice, if multiple actors happen to have arrived in the same place.
                    newPressureReleased = newPressureReleased + actor.currentPosition().flowRate() * newRemainingMinutes;
                }
            }
        }
        
        Set<Actor> newMes = updateActor(me);
        Set<Actor> newElephants = updateActor(elephant);
        
        Set<State> result = new HashSet<>();
        for (Actor newMe : newMes) {
            if (newElephants == null) {
                result.add(new State(network, newClosedValves, newRemainingMinutes, newPressureReleased, newMe, null));
            } else {
                for (Actor newElephant : newElephants) {
                    result.add(new State(network, newClosedValves, newRemainingMinutes, newPressureReleased, newMe, newElephant));
                }
            }
        }
        return result;
    }

    /**
     * @return all actors
     */
    private List<Actor> getActors() {
        return Stream.of(me, elephant).filter(Objects::nonNull).toList();
    }

    /**
     * Determines the next possible positions for the given actor.
     * 
     * @param actorToUpdate the actor for which to calculate new positions
     * @return new positions
     */
    private Set<Actor> updateActor(Actor actorToUpdate) {
        Set<Actor> result;
        if (actorToUpdate == null) {
            result = null;
        } else {
            result = new HashSet<>();
            if (actorToUpdate.currentPath() == null) {
                // Figure out which valves the actor could move to next.
                for (Valve closedValve : closedValves) {
                    if (getActors().stream().map(Actor::currentPosition).allMatch(currentPosition -> closedValve != currentPosition)) { // Do not go after the valve the other actor is already targeting
                        var path = network.getShortestPath(actorToUpdate.currentPosition(), closedValve);
                        if (path.size() + 2 < remainingMinutes) { // Only consider valves which the actor could get to in time.
                            result.add(new Actor(path.get(0), path.subList(1, path.size())));
                        }
                    }
                }
                if (result.isEmpty()) {
                    // Nowhere to go. The actor stays here.
                    result = Set.of(actorToUpdate);
                }
            } else if (actorToUpdate.currentPath().isEmpty()) {
                // Currently closing a valve.
                result.add(new Actor(actorToUpdate.currentPosition(), null));
            } else {
                // Moving along the path.
                Valve newPosition = actorToUpdate.currentPath().get(0);
                List<Valve> newPath = actorToUpdate.currentPath().subList(1, actorToUpdate.currentPath().size());
                result.add(new Actor(newPosition, newPath));
            }
        }
        return result;
    }
}
