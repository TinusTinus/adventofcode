package nl.mvdr.adventofcode.adventofcode2018.day24;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * State of the two armies.
 *
 * @author Martijn van de Rijdt
 */
class State {
    
    /** All groups (of both armies). */
    private final Set<Group> groups;
    
    /**
     * Parses the given input file into a state.
     * 
     * @param inputFilePath path to the input text file
     * @return set of groups
     * @throws IOException in case the input file could not be read
     */
    static State parse(Path inputFilePath) throws IOException {
        List<String> lines = Files.lines(inputFilePath)
            // ignore empty lines (the last line in the file)
            .filter(Objects::nonNull)
            .filter(line -> !line.isBlank())
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
        while (!state.fightingDone()) {
            state = state.fight();
        }
        return state;
    }
    
    /** @return whether fighting is done, that is, whether one of the armies has been defeated */
    private boolean fightingDone() {
        return groups.stream()
                .map(Group::getArmy)
                .count() < 2L;
    }
    
    /**
     * Performs a single round of combat.
     * 
     * @return state after the fight is complete
     */
    private State fight() {
        Map<Group, Optional<Group>> targets = performTargetSelection();
        
        return performAttacks(targets);
    }
    
    /**
     * Performs the target selection phase for this state.
     * 
     * @return map of attackers to their targets
     */
    private Map<Group, Optional<Group>> performTargetSelection() {
        Set<Group> remainingTargets = new HashSet<>(groups);
        
        return groups.stream()
                .sorted(Comparator.comparing(Group::effectivePower).reversed().thenComparing(Group::getInitiative))
                .collect(Collectors.toMap(Function.identity(), group -> {
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
    private State performAttacks(Map<Group, Optional<Group>> targets) {
        return null; // TODO implement!
    }

    /** @return total number of units of all groups */
    int totalUnits() {
        return groups.stream()
                .mapToInt(Group::getUnits)
                .sum();
    }
    
    @Override
    public String toString() {
        return "State:\n    " + groups.stream()
                .sorted(Comparator.comparing(Group::getArmy).thenComparing(Group::getId))
                .map(Group::toString)
                .collect(Collectors.joining("\n    "));
    }
}
