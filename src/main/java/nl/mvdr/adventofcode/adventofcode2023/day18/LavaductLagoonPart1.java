package nl.mvdr.adventofcode.adventofcode2023.day18;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.IntSolver;

/**
 * Solution to <a href="https://adventofcode.com/2023/day/18"Lavaduct Lagoon?</a>.
 *
 * @author Martijn van de Rijdt
 */
public class LavaductLagoonPart1 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(LavaductLagoonPart1.class);

    @Override
    public int solve(Stream<String> lines) {
        var digPlan = DigPlan.parse(lines);
        var hole = digPlan.digHole();
        return hole.size();
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        var instance = new LavaductLagoonPart1();

        var result = instance.solve("input-day18-2023.txt");

        LOGGER.info(result);
    }
}
 