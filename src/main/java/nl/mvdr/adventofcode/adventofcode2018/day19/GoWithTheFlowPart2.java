package nl.mvdr.adventofcode.adventofcode2018.day19;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Solution to the day 16 puzzle of 2018's Advent of Code:
 * <a href="https://adventofcode.com/2018/day/19">Go With The Flow</a>.
 *
 * @author Martijn van de Rijdt
 */
public class GoWithTheFlowPart2 extends GoWithTheFlow {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(GoWithTheFlowPart2.class);
    
    /** @return initial value for the registers */
    protected int[] getInitialRegisters() {
        return new int[] { 1, 0, 0, 0, 0, 0 };
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
