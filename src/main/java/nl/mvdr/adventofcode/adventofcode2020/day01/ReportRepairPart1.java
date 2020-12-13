package nl.mvdr.adventofcode.adventofcode2020.day01;

import java.util.function.Predicate;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.LongSolver;
import nl.mvdr.adventofcode.adventofcode2020.sums.Pair;

/**
 * Solution to the day 1 puzzle of 2020's Advent of Code:
 * <a href="https://adventofcode.com/2020/day/1">Report Repair</a>.
 *
 * @author Martijn van de Rijdt
 */
public class ReportRepairPart1 implements LongSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReportRepairPart1.class);

    /**
     * {@inheritDoc}
     * 
     * @return product of the two entries that sum to 2020 
     */
    @Override
    public long solve(Stream<String> lines) {
        long[] integers = lines.filter(Predicate.not(String::isEmpty))
            .mapToLong(Long::parseLong)
            .toArray();
        
        Pair pair = Pair.findPairWhichSumsTo(integers, 2020).orElseThrow();
        
        return pair.product();
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
 