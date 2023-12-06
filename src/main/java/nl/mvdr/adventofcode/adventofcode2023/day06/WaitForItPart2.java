package nl.mvdr.adventofcode.adventofcode2023.day06;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.LongSolver;

/**
 * Solution to <a href="https://adventofcode.com/2023/day/6">Wait For It</a>.
 *
 * @author Martijn van de Rijdt
 */
public class WaitForItPart2 implements LongSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(WaitForItPart2.class);

    @Override
    public long solve(Stream<String> linesStream) {
        var lines = linesStream.map(line -> line.replaceAll(" ", ""))
                .toList();
        var records = Record.parse(lines);
        if (records.size() != 1) {
            throw new IllegalStateException("Unexpected number of records (" + records.size() + ") found: " + records);
        }
        return records.getFirst().countWaysToBeat();
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        var instance = new WaitForItPart2();

        var result = instance.solve("input-day06-2023.txt");

        LOGGER.info(result);
    }
}
 