package nl.mvdr.adventofcode.adventofcode2022.day16;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A possible state of a network.
 *
 * @param closedValves valves which can still be opened to release pressure
 * @param remainingMinutes the remaining minutes until the vulcano erupts
 * @param pressureReleased amount of pressure released, as a result of the valves that have already been opened
 * @param me current position of the player character
 * @param elephant current position of the helper elephant; null if not applicable
 * @param distanceFunction function to get the distance from one valve to another
 * @author Martijn van de Rijdt
 */
record State(Set<Valve> closedValves, int remainingMinutes, int pressureReleased, Actor me, Actor elephant, BiFunction<Valve, Valve, Integer> distanceFunction) {

    /**
     * Constructor, for the initial state of a network.
     * 
     * @param network the network
     * @param helperElephant whether an elephant can help out
     */
    State(Network network, boolean helperElephant) {
        this(filterBrokenValves(network.valves()),
                helperElephant ? 26 : 30,
                0,
                new Actor(network.startingPoint(), null),
                helperElephant ? new Actor(network.startingPoint(), null) : null,
                network::getDistance);
    }

    /**
     * Filters broken valves from the given set of valves.
     * 
     * There is no point in opening these valves. It takes time to do but does not relieve any pressure.
     * 
     * @param valves set of valves
     * @return all valves except the broken ones where the flow rate is zero
     */
    private static Set<Valve> filterBrokenValves(Set<Valve> valves) {
        return valves.stream()
            .filter(valve -> 0 < valve.flowRate())
            .collect(Collectors.toSet());
    }
    
    /**
     * @return maximum amount of pressure that can be released starting from this state
     */
    int maxPressureReleased() {
        int result;
        if (remainingMinutes == 0) {
            result = pressureReleased;
        } else {
            result = 0;
            for (State state : nextStates()) {
                if (result < state.maxPressurePotential()) {
                    result = Integer.max(result, state.maxPressureReleased());
                }
            }
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
            if (Integer.valueOf(0).equals(actor.distance()) && newClosedValves.contains(actor.currentTarget())) {
                // The actor has reached their destination and can now close the valve.
                newClosedValves = new HashSet<>(newClosedValves);
                newClosedValves.remove(actor.currentTarget());
                newPressureReleased = newPressureReleased + pressureReleased(actor.currentTarget());
            }
        }
        
        Set<Actor> newMes = updateActor(me);
        Set<Actor> newElephants = updateActor(elephant);
        
        Set<State> result = new HashSet<>();
        for (Actor newMe : newMes) {
            if (newElephants == null) {
                result.add(new State(newClosedValves, newRemainingMinutes, newPressureReleased, newMe, null, distanceFunction));
            } else {
                for (Actor newElephant : newElephants) {
                    result.add(new State(newClosedValves, newRemainingMinutes, newPressureReleased, newMe, newElephant, distanceFunction));
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
            if (actorToUpdate.currentTarget() != null && actorToUpdate.distance() != null && 0 < actorToUpdate.distance().intValue()) {
                // Move along the path.
                result.add(new Actor(actorToUpdate.currentTarget(), Integer.valueOf(actorToUpdate.distance().intValue() - 1)));
            } else if (Integer.valueOf(0).equals(actorToUpdate.distance()) && closedValves.contains(actorToUpdate.currentTarget())) {
                // Currently closing a valve.
                result.add(new Actor(actorToUpdate.currentTarget(), null));
            } else {
                // Figure out which valves the actor could move to next.
                for (Valve closedValve : closedValves) {
                    if (getActors().stream().map(Actor::currentTarget).allMatch(currentPosition -> closedValve != currentPosition)) { // Do not go after the valve the other actor is already targeting
                        var pathLength = distanceFunction.apply(actorToUpdate.currentTarget(), closedValve).intValue();
                        if (pathLength + 1 < remainingMinutes) { // Only consider valves which the actor could get to in time to open.
                            result.add(new Actor(closedValve, Integer.valueOf(pathLength - 1)));
                        }
                    }
                }
                if (result.isEmpty()) {
                    // Nowhere to go. The actor stays here.
                    result = Set.of(actorToUpdate);
                }
            }
        }
        return result;
    }
    
    /**
     * Returns an upper bound for the amount of pressure that could still be released from this state.
     * The return value of this method can be computed pretty efficiently.
     * The idea is to use this to prune computation branches which may not yield a valuable result.
     * 
     * @return an upper bound for the amount of pressure that could still be released from this state
     */
    private int maxPressurePotential() {
        return pressureReleased + closedValves.stream()
                .mapToInt(this::pressureReleased)
                .sum();
    }
    
    /**
     * Computes the amount of pressure that would be released, if one started to open the given valve right now.
     * 
     * @param valve valve to open
     * @return pressure
     */
    private int pressureReleased(Valve valve) {
        return valve.flowRate() * (remainingMinutes - 1);
    }
}
