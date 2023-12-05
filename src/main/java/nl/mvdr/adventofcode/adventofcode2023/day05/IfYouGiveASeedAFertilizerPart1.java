package nl.mvdr.adventofcode.adventofcode2023.day05;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.LongSolver;

/**
 * Solution to <a href="https://adventofcode.com/2023/day/5">If You Give A Seed A Fertilizer</a>.
 *
 * @author Martijn van de Rijdt
 */
public class IfYouGiveASeedAFertilizerPart1 implements LongSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(IfYouGiveASeedAFertilizerPart1.class);

    @Override
    public long solve(Stream<String> lines) {
        var almanac = Almanac.parse(lines.toList());
        return almanac.getLowestLocationNumber();
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        var instance = new IfYouGiveASeedAFertilizerPart1();

        var result = instance.solve("input-day05-2023.txt");

        LOGGER.info(result);
    }
}
 