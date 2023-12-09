package nl.mvdr.adventofcode.adventofcode2023.day10;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.IntSolver;

/**
 * Solution to <a href="https://adventofcode.com/2023/day/10">?</a>.
 *
 * @author Martijn van de Rijdt
 */
public class Day10Part1 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(Day10Part1.class);

    @Override
    public int solve(Stream<String> lines) {
        return 0; // TODO
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        var instance = new Day10Part1();

        var result = instance.solve("input-day10-2023.txt");

        LOGGER.info(result);
    }
}
 