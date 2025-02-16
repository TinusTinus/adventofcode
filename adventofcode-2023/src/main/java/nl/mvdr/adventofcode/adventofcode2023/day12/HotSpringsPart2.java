package nl.mvdr.adventofcode.adventofcode2023.day12;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.solver.LongSolver;

/**
 * Solution to <a href="https://adventofcode.com/2023/day/12">Hot Springs</a>.
 *
 * @author Martijn van de Rijdt
 */
public class HotSpringsPart2 implements LongSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(HotSpringsPart2.class);

    @Override
    public long solve(Stream<String> lines) {
        return lines.map(ConditionRecord::parse)
                .map(ConditionRecord::unfold)
                .mapToLong(ConditionRecord::countArrangements)
                .sum();
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        var instance = new HotSpringsPart2();

        var result = instance.solve("input-day12-2023.txt");

        LOGGER.info(result);
    }
}
 