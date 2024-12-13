package nl.mvdr.adventofcode.adventofcode2022.day03;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.solver.IntSolver;

/**
 * Solution to the day 3 puzzle of 2022's Advent of Code:
 * <a href="https://adventofcode.com/2022/day/03">Rucksack Reorganization</a>.
 *
 * @author Martijn van de Rijdt
 */
public class RucksackReorganizationPart1 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(RucksackReorganizationPart1.class);

    @Override
    public int solve(Stream<String> lines) {
        return lines.map(Rucksack::parse)
                .map(Rucksack::commonItem)
                .mapToInt(Item::priority)
                .sum();
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        var instance = new RucksackReorganizationPart1();

        var result = instance.solve("input-day03-2022.txt");

        LOGGER.info(result);
    }
}
 