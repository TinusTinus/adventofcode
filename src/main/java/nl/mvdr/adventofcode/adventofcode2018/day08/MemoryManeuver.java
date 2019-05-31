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
public class MemoryManeuver implements PathSolver {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(MemoryManeuver.class);
    
    @Override
    public String solve(Path inputFilePath) throws IOException {
        Tree tree = Tree.parse(inputFilePath);
        
        return "" + tree.sum();
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        MemoryManeuver instance = new MemoryManeuver();

        String result = instance.solve("input-day08-2018.txt");

        LOGGER.info(result);
    }
}
