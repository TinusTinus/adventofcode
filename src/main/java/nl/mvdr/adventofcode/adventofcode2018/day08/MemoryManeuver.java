package nl.mvdr.adventofcode.adventofcode2018.day08;

import java.io.IOException;
import java.nio.file.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.PathSolver;

/**
 * Solution to the day 8 puzzle of 2018's Advent of Code:
 * <a href="https://adventofcode.com/2018/day/8">Memory Maneuver</a>.
 *
 * @author Martijn van de Rijdt
 */
public abstract class MemoryManeuver implements PathSolver {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(MemoryManeuver.class);
    
    @Override
    public String solve(Path inputFilePath) throws IOException {
        Tree tree = Tree.parse(inputFilePath);
        
        LOGGER.debug("Tree: {}", tree);
        
        return solve(tree);
    }
    
    /**
     * Solver method.
     * 
     * @param tree input tree
     * @return solution to the puzzle for the given tree
     */
    protected abstract String solve(Tree tree);
}
