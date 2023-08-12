package nl.mvdr.adventofcode.adventofcode2022.day19;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

import org.apache.commons.collections4.MultiSet;
import org.apache.commons.collections4.multiset.HashMultiSet;

/**
 * Current state while cracking geodes.
 *
 * @param remainingMinutes remaining time in minutes
 * @param resources currently available resources
 * @param robots currently available robots
 * @param robotUnderConstruction robot currently under construction; may be null if not applicable
 * @param constructionTime remaining time needed to complete the construction; only has meaning if a robot is under construction
 * @author Martijn van de Rijdt
 */
record State(int remainingTime, MultiSet<Resource> resources, MultiSet<Resource> robots) {
    
    /**
     * @return initial state
     */
    static State createInitialState() {
        int remainingTime = 24;
        // Start without resources
        MultiSet<Resource> resources = new HashMultiSet<>();
        // Start with one ore-collecting robot
        MultiSet<Resource> robots = new HashMultiSet<>();
        robots.add(Resource.ORE);

        return new State(remainingTime, resources, robots);
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
            result = getNextStates(blueprint).stream()
                    .mapToInt(state -> state.computeMaxGeodes(blueprint))
                    .max()
                    .orElseThrow();
        }
        return result;
    }
    
    /**
     * @return the possible next states starting from this state
     */
    Set<State> getNextStates(Blueprint blueprint) {
        Set<State> result = new HashSet<>();
        result.add(doNothing());
        Stream.of(Resource.values())
                .map(type -> buildRobot(type, blueprint))
                .filter(Objects::nonNull)
                .forEach(result::add);
        return result;
    }
    
    /**
     * Returns the next state, assuming that we do not build a new robot.
     * 
     * @return next state
     */
    private State doNothing() {
        if (remainingTime < 1) {
            throw new IllegalStateException("No more time!");
        }
        int newRemainingTime = remainingTime - 1;
        
        MultiSet<Resource> newResources = new HashMultiSet<>(resources);
        newResources.addAll(robots); // each robot collects 1 resource of its type
        
        MultiSet<Resource> newRobots = new HashMultiSet<>(robots);
        
        return new State(newRemainingTime, newResources, newRobots);
    }
    
    /**
     * Returns the next state, assuming that we build a new robot of the given type.
     * 
     * @param type resource type which the new robot will collect
     * @param blueprint the blueprint to follow when building the robot
     * @return next state, or null if a robot of the given type cannot be built 
     */
    private State buildRobot(Resource type, Blueprint blueprint) {
        var requirement = blueprint.resourceRequirements().get(type);
        var canBuild = Stream.of(Resource.values())
                .allMatch(resource -> requirement.requires(resource) <= resources.getCount(resource));
        
        State result;
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
            
            result = new State(newRemainingTime, newResources, newRobots);
        } else {
            result = null;
        }
        return result;
    }
    
    /**
     * Checks whether it is possible to build a robot for the given resource type, using the resources currently available.
     * 
     * @param robot type of resource for the new robot to gather
     * @param blueprint blueprint to be used for construction of the robot
     * @return whether the robot can be built
     */
    private boolean canBuild(Resource robot, Blueprint blueprint) {
        var requirement = blueprint.resourceRequirements().get(robot);
        return Stream.of(Resource.values())
                .allMatch(resource -> requirement.requires(resource) <= resources.getCount(resource));
    }
    
}
