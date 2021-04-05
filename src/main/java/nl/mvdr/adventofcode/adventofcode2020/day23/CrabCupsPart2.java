package nl.mvdr.adventofcode.adventofcode2020.day23;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.LongSolver;

/**
 * Solution to the day 23 puzzle of 2020's Advent of Code:
 * <a href="https://adventofcode.com/2020/day/23">Crab Cups</a>.
 *
 * @author Martijn van de Rijdt
 */
public class CrabCupsPart2 implements LongSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(CrabCupsPart2.class);

    /**
     * {@inheritDoc}
     * 
     * @return product of the labels of the cups containing the two stars
     */
    @Override
    public long solve(Stream<String> lines) {
        return GameState.parseCorrectTranslation(lines)
                .productOfCupsContainingStars();
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        CrabCupsPart2 instance = new CrabCupsPart2();

        String result = instance.solve("input-day23-2020.txt");

        LOGGER.info(result);
    }
}
 