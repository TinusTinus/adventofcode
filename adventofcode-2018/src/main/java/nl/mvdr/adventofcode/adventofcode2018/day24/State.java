package nl.mvdr.adventofcode.adventofcode2018.day24;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * State of the two armies.
 *
 * @author Martijn van de Rijdt
 */
class State {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(State.class);
    
    /** All groups (of both armies). */
    private final Set<Group> groups;
    
    /**
     * Parses the given input file into a state.
     * 
     * @param linesStream contents of the input text file
     * @return set of groups
     */
    static State parse(Stream<String> linesStream) {
        List<String> lines = linesStream.filter(Predicate.not(String::isBlank))
            .collect(Collectors.toList());
        
        Set<Group> groups = new HashSet<>();
        Army currentArmy = null;
        int id = 1;
        for (String line : lines) {
            if (line.startsWith(Army.IMMUNE_SYSTEM.toString())) {
                currentArmy = Army.IMMUNE_SYSTEM;
                id = 1;
            } else if (line.startsWith(Army.INFECTION.toString())) {
                currentArmy = Army.INFECTION;
                id = 1;
            } else {
                groups.add(Group.parse(currentArmy, id, line));
                id++;
            }
        }
        
        return new State(groups);
    }
    
    /**
     * Constructor.
     * 
     * @param groups all groups (of both armies)
     */
    private State(Set<Group> groups) {
        super();
        this.groups = groups;
    }
    
    /**
     * Lets the armies repeatedly fight each other until one has been defeated.
     * 
     * @return end state
     */
    State fightUntilDone() {
        State state = this;
        
        LOGGER.debug("Start {}", state);
        
        while (!state.fightingDone()) {
            state = state.fight();
            
            LOGGER.debug("Round of fighting complete, {}", state);
        }
        return state;
    }
    
    /** @return whether fighting is done, that is, whether one of the armies has been defeated */
    private boolean fightingDone() {
        return winner().isPresent();
    }
    
    /** @return the winner of this battle, if it has concluded; empty otherwise */
    Optional<Army> winner() {
        Set<Army> remainingArmies = groups.stream()
                .map(Group::getGroupIdentification)
                .map(GroupIdentification::getArmy)
                .collect(Collectors.toSet());
        
        Optional<Army> result;
        if (remainingArmies.size() == 1) {
            result = Optional.of(remainingArmies.iterator().next());
        } else {
            result = Optional.empty();
        }
        return result;
    }
    
    /**
     * Performs a single round of combat.
     * 
     * @return state after the fight is complete
     */
    private State fight() {
        Map<GroupIdentification, Optional<Group>> targets = performTargetSelection();
        
        return performAttacks(targets);
    }
    
    /**
     * Performs the target selection phase for this state.
     * 
     * @return map of attackers to their targets
     */
    private Map<GroupIdentification, Optional<Group>> performTargetSelection() {
        Set<Group> remainingTargets = new HashSet<>(groups);
        
        return groups.stream()
                .sorted(Comparator.comparing(Group::effectivePower).reversed().thenComparingInt(group -> -group.getInitiative()))
                .collect(Collectors.toMap(Group::getGroupIdentification, group -> {
                    Optional<Group> target = group.selectTarget(remainingTargets);
                    target.ifPresent(remainingTargets::remove);
                    return target;
                }));
    }
    
    /**
     * Performs the attacking phase for this state.
     * 
     * @param targets map of attackers to their targets
     * @return new state after attacks have been resolved
     */
    private State performAttacks(Map<GroupIdentification, Optional<Group>> targets) {
        Set<Group> nextGroups = new HashSet<>(this.groups);
        
        targets.keySet().stream()
                // Attack in decreasing initiative order
                .sorted(Comparator.comparingInt(id -> -getGroup(id).getInitiative()))
                // Look up the attacker (may no longer exist, or have lost units, because of earlier attacks this round)
                .map(id -> nextGroups.stream().filter(g -> g.getGroupIdentification().equals(id)).findAny())
                .filter(Optional::isPresent)
                .map(Optional::get)
                .forEach(attacker -> {
                    // Lookup the target (could not have been attacked yet this round)
                    targets.get(attacker.getGroupIdentification())
                            .map(target -> removeAndReturn(nextGroups, target))
                            // Perform the attack
                            .flatMap(attacker::attack)
                            // Add updated target, if any units survived
                            .ifPresent(nextGroups::add);
                });
        
        return new State(nextGroups);
    }
    
    private Group getGroup(GroupIdentification id) {
        return this.groups.stream()
                .filter(group -> group.getGroupIdentification().equals(id))
                .findAny()
                .get();
    }

    /**
     * Removes the given element from the given set, and returns it.
     * 
     * Convenience method for method chaining.
     * 
     * @param set set from which the element should be removed
     * @param element element to be removed
     * @return input element
     */
    private <E> E removeAndReturn(Set<E> set, E element) {
        set.remove(element);
        return element;
    }

    /** @return total number of units of all groups */
    int totalUnits() {
        return groups.stream()
                .mapToInt(Group::getUnits)
                .sum();
    }
    
    /**
     * Boosts the Immune System's army.
     * 
     * @param boost integer increase in immune system units' attack damage
     * @return updated state
     */
    State boostImmuneSystem(int boost) {
        Set<Group> nextGroups = groups.stream()
                .map(group -> group.boostImmuneSystem(boost))
                .collect(Collectors.toSet());
        return new State(nextGroups);
    }
    
    @Override
    public String toString() {
        return "State:\n    " + groups.stream()
                .sorted(Comparator.comparing(Group::getGroupIdentification))
                .map(Group::toString)
                .collect(Collectors.joining("\n    "));
    }
}
