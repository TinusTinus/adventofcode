package nl.mvdr.adventofcode.adventofcode2022.day18;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.solver.LongSolver;

/**
 * Solution to <a href="https://adventofcode.com/2022/day/18">Boiling Boulders</a>.
 *
 * @author Martijn van de Rijdt
 */
public class BoilingBouldersPart1 implements LongSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(BoilingBouldersPart1.class);

    @Override
    public long solve(Stream<String> lines) {
        return new BoilingBoulders(false).solve(lines);
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        var instance = new BoilingBouldersPart1();

        var result = instance.solve("input-day18-2022.txt");

        LOGGER.info(result);
    }
}
 