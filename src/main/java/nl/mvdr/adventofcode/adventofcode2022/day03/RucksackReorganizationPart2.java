package nl.mvdr.adventofcode.adventofcode2022.day03;

import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.IntSolver;

/**
 * Solution to the day 3 puzzle of 2022's Advent of Code:
 * <a href="https://adventofcode.com/2022/day/03">Rucksack Reorganization</a>.
 *
 * @author Martijn van de Rijdt
 */
public class RucksackReorganizationPart2 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(RucksackReorganizationPart2.class);

    private static final int GROUP_SIZE = 3;
    
    @Override
    public int solve(Stream<String> lines) {
        var rucksacks = lines.map(Rucksack::parse)
                .collect(Collectors.toList());

        if (rucksacks.size() % GROUP_SIZE != 0) {
            throw new IllegalArgumentException("Unexpected number of rucksacks: " + rucksacks.size());
        }
        
        return IntStream.range(0, rucksacks.size() / GROUP_SIZE)
                .map(i -> i * GROUP_SIZE)
                .mapToObj(i -> rucksacks.subList(i, i + GROUP_SIZE))
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
        var instance = new RucksackReorganizationPart2();

        var result = instance.solve("input-day03-2022.txt");

        LOGGER.info(result);
    }
}
 