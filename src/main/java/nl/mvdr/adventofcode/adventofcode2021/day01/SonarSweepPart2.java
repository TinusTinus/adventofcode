package nl.mvdr.adventofcode.adventofcode2021.day01;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.LongSolver;

/**
 * Solution to the day 1 puzzle of 2021's Advent of Code:
 * <a href="https://adventofcode.com/2021/day/1">Sonar Sweep</a>.
 *
 * @author Martijn van de Rijdt
 */
public class SonarSweepPart2 implements LongSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(SonarSweepPart2.class);

    /**
     * {@inheritDoc}
     * 
     * @return score
     */
    @Override
    public long solve(Stream<String> lines) {
        List<Integer> report = lines.map(Integer::valueOf)
                .toList();
        return IntStream.range(3, report.size())
                .filter(i -> report.get(i - 3).intValue() < report.get(i).intValue()) // Note that we can ignore i-2 and i-1, as they would occur on both sides.
                .count();
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        var instance = new SonarSweepPart2();

        var result = instance.solve("input-day01-2021.txt");

        LOGGER.info(result);
    }
}
 