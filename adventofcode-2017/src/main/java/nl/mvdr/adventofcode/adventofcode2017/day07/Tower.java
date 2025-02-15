package nl.mvdr.adventofcode.adventofcode2017.day07;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Representation of a tower of programs.
 *
 * @author Martijn van de Rijdt
 */
public class Tower {
    private static final Logger LOGGER = LoggerFactory.getLogger(Tower.class);
    
    /** The program holding up this tower. */
    private final Program program;
    
    /** This tower's subtowers. */
    private final Set<Tower> subtowers;

    /**
     * Gets the tower containing the given programs.
     * 
     * @param programs collection of programs
     * @return tower containing these programs
     */
    static Tower getTower(Collection<Program> programs) {
        Program base = determineBase(programs);
        return getTower(base, programs);
    }
    
    /**
     * Gets the tower containing (a subset of) the given programs, with the given program at its base.
     * 
     * @param base base of the tower
     * @param programs collection of programs
     * @return tower containing these programs
     */
    private static Tower getTower(Program base, Collection<Program> programs) {
        Set<Tower> subtowers = base.getNamesHeldUp().stream()
                .map(name -> programs.stream().filter(program -> program.getName().equals(name)).findAny().orElseThrow())
                .map(program -> Tower.getTower(program, programs))
                .collect(Collectors.toSet());
        
        return new Tower(base, subtowers);
    }
    
    /**
     * Determines which program is the base of the tower formed by the given collection of programs.
     * 
     * @param programs programs
     * @return base
     */
    static Program determineBase(Collection<Program> programs) {
        return programs.stream()
                .filter(program -> programs.stream().noneMatch(otherProgram -> otherProgram.isHoldingUp(program.getName())))
                .findAny()
                .orElseThrow();
    }
    
    /**
     * Constructor.
     * 
     * @param program the program holding up this tower
     * @param subtowers this tower's subtowers
     */
    private Tower(Program program, Set<Tower> subtowers) {
        super();
        this.program = program;
        this.subtowers = subtowers;
    }
    
    Set<Tower> getSubtowers() {
        return subtowers;
    }
    
    Program getProgram() {
        return program;
    }
    
    /**
     * Total weight of this tower.
     * 
     * @return total weight of this tower
     */
    int totalWeight() {
        int subtowerWeight = subtowers.stream()
                .mapToInt(Tower::totalWeight)
                .sum();
        
        return program.getWeight() + subtowerWeight;
    }
    
    /** @return whether this tower is balanced */
    private boolean isBalanced() {
        return subtowers.stream()
                .mapToInt(Tower::totalWeight)
                .distinct()
                .count() < 2L;
    }
    
    /**
     * @return the smallest subtower of this tower which is unbalanced
     */
    Tower findSmallestUnbalancedSubtower() {
        LOGGER.debug("Finding the smallest unbalanced subtower for {}", this);
        
        if (isBalanced()) {
            throw new IllegalStateException("This tower is perfectly balanced.");
        }
        
        return subtowers.stream()
                .filter(tower -> !tower.isBalanced())
                .findAny()
                .map(Tower::findSmallestUnbalancedSubtower)
                .orElse(this);
    }
    
    @Override
    public String toString() {
        return program.getName();
    }
}
