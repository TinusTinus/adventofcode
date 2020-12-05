package nl.mvdr.adventofcode.adventofcode2020.day01;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.IntSolver;

/**
 * Solution to the day 1 puzzle of 2020's Advent of Code:
 * <a href="https://adventofcode.com/2020/day/1">Report Repair</a>.
 *
 * @author Martijn van de Rijdt
 */
public class ReportRepairPart1 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReportRepairPart1.class);

    /**
     * {@inheritDoc}
     * 
     * @return product of the two entries that sum to 2020 
     */
    @Override
    public int solve(Stream<String> lines) {
        int[] integers = lines.filter(Predicate.not(String::isEmpty))
            .mapToInt(Integer::parseInt)
            .toArray();
        
        return IntStream.range(0, integers.length)
            .mapToObj(i -> IntStream.range(i + 1, integers.length).mapToObj(j -> new Pair(integers[i], integers[j])))
            .flatMap(Function.identity())
            .peek(pair -> LOGGER.debug("Pair: {}", pair))
            .filter(pair -> pair.sum() == 2020)
            .mapToInt(Pair::product)
            .findFirst()
            .orElseThrow();
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        ReportRepairPart1 instance = new ReportRepairPart1();

        String result = instance.solve("input-day01-2020.txt");

        LOGGER.info(result);
    }
}
 