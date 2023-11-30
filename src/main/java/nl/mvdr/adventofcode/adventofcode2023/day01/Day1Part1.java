package nl.mvdr.adventofcode.adventofcode2023.day01;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.IntSolver;

/**
 * Solution to the day 1 puzzle of 2023's Advent of Code:
 * <a href="https://adventofcode.com/2023/day/1">?</a>.
 *
 * @author Martijn van de Rijdt
 */
public class Day1Part1 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(Day1Part1.class);

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
        Day1Part1 instance = new Day1Part1();

        String result = instance.solve("input-day01-2023.txt");

        LOGGER.info(result);
    }
}
 