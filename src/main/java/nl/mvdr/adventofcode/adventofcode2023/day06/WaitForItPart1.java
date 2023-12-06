package nl.mvdr.adventofcode.adventofcode2023.day06;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.IntSolver;

/**
 * Solution to <a href="https://adventofcode.com/2023/day/6">Wait For It</a>.
 *
 * @author Martijn van de Rijdt
 */
public class WaitForItPart1 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(WaitForItPart1.class);

    @Override
    public int solve(Stream<String> lines) {
        var records = Record.parse(lines.toList());
        return records.stream()
                .mapToInt(Record::countWaysToBeat)
                .reduce(1, (i, j) -> i * j);
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        var instance = new WaitForItPart1();

        var result = instance.solve("input-day06-2023.txt");

        LOGGER.info(result);
    }
}
 