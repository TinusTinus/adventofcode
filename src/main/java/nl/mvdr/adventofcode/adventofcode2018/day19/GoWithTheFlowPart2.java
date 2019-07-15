package nl.mvdr.adventofcode.adventofcode2018.day19;

import java.io.IOException;
import java.nio.file.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.PathSolver;

/**
 * Solution to the day 19 puzzle of 2018's Advent of Code:
 * <a href="https://adventofcode.com/2018/day/19">Go With The Flow</a>.
 *
 * @author Martijn van de Rijdt
 */
public class GoWithTheFlowPart2 implements PathSolver {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(GoWithTheFlowPart2.class);
    
    @Override
    public String solve(Path inputFilePath) throws IOException {
        // We could simply run the instructions with initial register values { 1, 0, 0, 0, 0, 0 }.
        // This would result in a correct answer, but would take a very long time to terminate.
        // As it turns out, the program calculates the sum of the divisors of 10551330 (?).
        return null; // TODO sum of divisors
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        GoWithTheFlowPart2 solver = new GoWithTheFlowPart2();
        String solution = solver.solve("input-day19-2018.txt");
        LOGGER.info(solution);
    }
}
