package nl.mvdr.adventofcode.adventofcode2018.day08;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Solution to part 2 of the day 8 puzzle of 2018's Advent of Code:
 * <a href="https://adventofcode.com/2018/day/8">Memory Maneuver</a>.
 *
 * @author Martijn van de Rijdt
 */
public class MemoryManeuverPart2 extends MemoryManeuver {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(MemoryManeuverPart2.class);
    
    @Override
    public String solve(Tree tree) {
        return "" + tree.value();
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        MemoryManeuverPart2 instance = new MemoryManeuverPart2();

        String result = instance.solve("input-day08-2018.txt");

        LOGGER.info(result);
    }
}
