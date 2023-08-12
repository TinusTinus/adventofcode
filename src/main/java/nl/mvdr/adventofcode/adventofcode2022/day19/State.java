package nl.mvdr.adventofcode.adventofcode2022.day19;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import org.apache.commons.collections4.MultiSet;
import org.apache.commons.collections4.multiset.HashMultiSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Current state while cracking geodes.
 *
 * @param remainingMinutes remaining time in minutes
 * @param resources currently available resources
 * @param robots currently available robots
 * @param robotUnderConstruction robot currently under construction; may be null if not applicable
 * @param constructionTime remaining time needed to complete the construction; only has meaning if a robot is under construction
 * @param passedUpRobots robots which we could have built a minute ago, but chose not to; there is no point in building such a robot now
 * @author Martijn van de Rijdt
 */
record State(int remainingTime, MultiSet<Resource> resources, MultiSet<Resource> robots, Set<Resource> passedUpRobots) {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(State.class);
    
    /**
     * Creates the initial state.
     * 
     * @param time available time in minutes
     * @return initial state
     */
    static State createInitialState(int time ) {
        // Start without resources
        MultiSet<Resource> resources = new HashMultiSet<>();
        // Start with one ore-collecting robot
        MultiSet<Resource> robots = new HashMultiSet<>();
        robots.add(Resource.ORE);
        Set<Resource> passedUpRobots = EnumSet.noneOf(Resource.class);

        return new State(time, resources, robots, passedUpRobots);
    }
    
    /**
     * Computes the maximum number of geodes which can be opened, starting from this state.
     * 
     * @param blueprint the blueprint to follow
     * @return maximum number of geodes
     */
    int computeMaxGeodes(Blueprint blueprint) {
        int result;
        if (remainingTime == 0) {
            result = resources.getCount(Resource.GEODE);
        } else {
            result = 0;
            for (State state : getNextStates(blueprint)) {
                if (result < state.upperBound()) {
                    result = Math.max(result, state.computeMaxGeodes(blueprint));
                    if (20 < remainingTime) {
                        LOGGER.trace("Max geodes found so far for blueprint {} with {} remaining minutes: {}",
                                Integer.valueOf(blueprint.id()),
                                Integer.valueOf(remainingTime),
                                Integer.valueOf(result));
                    }
                }
            }
        }
        return result;
    }
    
    /**
     * @return the possible next states starting from this state, in the order in which they should be investigated
     */
    List<State> getNextStates(Blueprint blueprint) {
        Map<Resource, State> states = new HashMap<>();
        for (Resource type : Resource.values()) {
            State state = buildRobot(type, blueprint);
            if (state != null) {
                states.put(type, state);
            }
        }
        
        // Return them in order of most promising action to least promising: geode, obsidian, clay, ore, nothing
        List<State> result = new ArrayList<>();
        Stream.of(Resource.values())
                .sorted(Comparator.reverseOrder())
                .filter(states::containsKey)
                .map(states::get)
                .forEach(result::add);
        result.add(doNothing(states.keySet()));
        return result;
    }
    
    /**
     * Returns the next state, assuming that we build a new robot of the given type.
     * 
     * @param type resource type which the new robot will collect
     * @param blueprint the blueprint to follow when building the robot
     * @return next state, or null if a robot of the given type cannot be built 
     */
    private State buildRobot(Resource type, Blueprint blueprint) {
        State result;
        if (isUseful(type, blueprint)) {
            var requirement = blueprint.resourceRequirements().get(type);
            var canBuild = Stream.of(Resource.values())
                    .allMatch(resource -> requirement.requires(resource) <= resources.getCount(resource));
            if (canBuild) {
                if (remainingTime < 1) {
                    throw new IllegalStateException("No more time!");
                }
                int newRemainingTime = remainingTime - 1;
                
                MultiSet<Resource> newResources = new HashMultiSet<>(resources);
                // Remove resources used to build the robot
                Stream.of(Resource.values()).forEach(resource -> newResources.remove(resource, requirement.requires(resource)));
                // Each existing robot collects 1 resource of its type
                newResources.addAll(robots);
                
                MultiSet<Resource> newRobots = new HashMultiSet<>(robots);
                // Add the new robot
                newRobots.add(type);
                
                result = new State(newRemainingTime, newResources, newRobots, EnumSet.noneOf(Resource.class));
            } else {
                // Cannot build
                result = null;
            }
        } else {
            // Not useful
            result = null;
        }
        return result;
    }
    
    /**
     * Determines whether it would be useful to build a robot of the given type.
     * 
     * @param type robot tpye
     * @param blueprint the blueprint to follow when building the robot
     * @return whether it could be useful to build a robot of this type
     */
    private boolean isUseful(Resource type, Blueprint blueprint) {
        boolean result;
        if (type == Resource.GEODE) {
            // Building a geode-cracking robot is always useful.
            result = true;
        } else if (remainingTime == 1) {
            // There is no point in building an ore / clay / obsidian robot in the last minute.
            // It will not produce anything which can lead to more open geodes.
            result = false;
        } else if (passedUpRobots.contains(type)) {
            // It would be strictly better to have built this robot a minute earlier.
            // Building it now will not result in an end state with more geodes.
            result = false;
        } else {
            // If we're already producing enough of this resource per minute to satisfy any requirements,
            // there is no point in making another robot that produces more of this resource.
            var maxResourceRequired = blueprint.resourceRequirements()
                    .values()
                    .stream()
                    .mapToInt(requirement -> requirement.requires(type))
                    .max()
                    .orElseThrow();
            result = robots.getCount(type) < maxResourceRequired;
        }
        return result;
    }

    /**
     * Returns the next state, assuming that we do not build a new robot.
     * 
     * @param passedUpThisTurn robots which we could have built, but chose not to
     * @return next state
     */
    private State doNothing(Set<Resource> passedUpThisTurn) {
        if (remainingTime < 1) {
            throw new IllegalStateException("No more time!");
        }
        int newRemainingTime = remainingTime - 1;
        
        MultiSet<Resource> newResources = new HashMultiSet<>(resources);
        newResources.addAll(robots); // each robot collects 1 resource of its type
        
        MultiSet<Resource> newRobots = new HashMultiSet<>(robots);
        
        Set<Resource> newPassedUp = EnumSet.copyOf(passedUpRobots);
        newPassedUp.addAll(passedUpThisTurn);
        
        return new State(newRemainingTime, newResources, newRobots, newPassedUp);
    }
    
    /**
     * Computes an upper bound for the number of geodes that could be produced by this state.
     * 
     * The idea is that this upper bound can be computed a lot more efficiently than the actual number,
     * so that it can be used to prevent investigating states with no hope of resulting in anything good.
     * 
     * @return upper bound
     */
    private int upperBound() {
        return resources.getCount(Resource.GEODE) // geodes already open 
                + robots.getCount(Resource.GEODE) * remainingTime // geodes which will be opened by current robots
                + remainingTime * (remainingTime + 1) / 2; // geodes which would be opened if we were to build a geode robot every minute from now
    }
}
