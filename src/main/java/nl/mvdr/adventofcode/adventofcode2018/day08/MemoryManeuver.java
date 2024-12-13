package nl.mvdr.adventofcode.adventofcode2018.day08;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.solver.IntSolver;

/**
 * Solution to the day 8 puzzle of 2018's Advent of Code:
 * <a href="https://adventofcode.com/2018/day/8">Memory Maneuver</a>.
 *
 * @author Martijn van de Rijdt
 */
public abstract class MemoryManeuver implements IntSolver {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(MemoryManeuver.class);
    
    @Override
    public int solve(Stream<String> lines) {
        // All of the input is on the first line of the text file.
        String line = lines.findFirst().get();
        Tree tree = Tree.parse(line);
        
        LOGGER.debug("Tree: {}", tree);
        
        return solve(tree);
    }
    
    /**
     * Solver method.
     * 
     * @param tree input tree
     * @return solution to the puzzle for the given tree
     */
    protected abstract int solve(Tree tree);
}
