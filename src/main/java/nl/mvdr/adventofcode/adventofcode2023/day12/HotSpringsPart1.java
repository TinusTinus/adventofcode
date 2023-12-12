package nl.mvdr.adventofcode.adventofcode2023.day12;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.LongSolver;

/**
 * Solution to <a href="https://adventofcode.com/2023/day/12">Hot Springs</a>.
 *
 * @author Martijn van de Rijdt
 */
public class HotSpringsPart1 implements LongSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(HotSpringsPart1.class);

    @Override
    public long solve(Stream<String> lines) {
        return lines.map(ConditionRecord::parse)
                .mapToLong(ConditionRecord::countArrangements)
                .sum();
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        var instance = new HotSpringsPart1();

        var result = instance.solve("input-day12-2023.txt");

        LOGGER.info(result);
    }
}
 