package nl.mvdr.adventofcode.adventofcode2022.day04;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.solver.LongSolver;

/**
 * Solution to <a href="https://adventofcode.com/2022/day/04">Camp Cleanup</a>.
 *
 * @author Martijn van de Rijdt
 */
public class CampCleanupPart1 implements LongSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(CampCleanupPart1.class);

    @Override
    public long solve(Stream<String> lines) {
        return lines.map(Pair::parse)
                .filter(Pair::fullyContains)
                .count();
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        var instance = new CampCleanupPart1();

        var result = instance.solve("input-day04-2022.txt");

        LOGGER.info(result);
    }
}
 