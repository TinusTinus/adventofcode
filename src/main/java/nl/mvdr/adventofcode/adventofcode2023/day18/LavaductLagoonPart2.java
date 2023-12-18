package nl.mvdr.adventofcode.adventofcode2023.day18;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.LongSolver;

/**
 * Solution to <a href="https://adventofcode.com/2023/day/18"Lavaduct Lagoon?</a>.
 *
 * @author Martijn van de Rijdt
 */
public class LavaductLagoonPart2 implements LongSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(LavaductLagoonPart2.class);

    @Override
    public long solve(Stream<String> lines) {
        var digPlan = DigPlan.parse(lines, false);
        return digPlan.holeSize();
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        var instance = new LavaductLagoonPart2();
        
        var result = instance.solve("input-day18-2023.txt");

        LOGGER.info(result);
    }
}
 