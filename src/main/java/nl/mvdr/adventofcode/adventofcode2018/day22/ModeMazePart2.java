package nl.mvdr.adventofcode.adventofcode2018.day22;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Solution to part 2 of the day 22 puzzle of 2018's Advent of Code:
 * <a href="https://adventofcode.com/2018/day/22">Mode Maze</a>.
 * 
 * @author Martijn van de Rijdt
 */
public class ModeMazePart2 extends ModeMaze {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ModeMazePart2.class);
    
    /**
     * Solver method.
     * 
     * @param cave the cave
     * @return integer representing the puzzle's result
     */
    @Override
    protected int solve(Cave cave) {
        return 0; // TODO
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        ModeMazePart2 solver = new ModeMazePart2();
        String solution = solver.solve("input-day22-2018.txt");
        LOGGER.info(solution);
    }
}
