package nl.mvdr.adventofcode.adventofcode2020.day06;

import java.util.Set;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.solver.IntSolver;

/**
 * Solution to the day 6 puzzle of 2020's Advent of Code:
 * <a href="https://adventofcode.com/2020/day/6">Custom Customs</a>.
 *
 * @author Martijn van de Rijdt
 */
public class CustomCustomsPart2 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomCustomsPart2.class);

    /**
     * {@inheritDoc}
     * 
     * @return sum of the question counts
     */
    @Override
    public int solve(Stream<String> lines) {
        return Group.parse(lines)
                .stream()
                .map(Group::questionsAll)
                .mapToInt(Set::size)
                .sum();
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        CustomCustomsPart2 instance = new CustomCustomsPart2();

        String result = instance.solve("input-day06-2020.txt");

        LOGGER.info(result);
    }
}
 