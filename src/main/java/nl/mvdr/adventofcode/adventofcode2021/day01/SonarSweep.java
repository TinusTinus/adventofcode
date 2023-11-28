package nl.mvdr.adventofcode.adventofcode2021.day01;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import nl.mvdr.adventofcode.LongSolver;

/**
 * Solution to the day 1 puzzle of 2021's Advent of Code:
 * <a href="https://adventofcode.com/2021/day/1">Sonar Sweep</a>.
 *
 * @author Martijn van de Rijdt
 */
public class SonarSweep implements LongSolver {

    private final int slidingWindowSize;
    
    /**
     * Constructor.
     * 
     * @param slidingWindowSize size of the sliding window to compare (1 to compare individual elements)
     */
    SonarSweep(int slidingWindowSize) {
        super();
        this.slidingWindowSize = slidingWindowSize;
    }
    
    @Override
    public long solve(Stream<String> lines) {
        List<Integer> report = lines.map(Integer::valueOf)
                .toList();
        return IntStream.range(slidingWindowSize, report.size())
                .filter(i -> report.get(i - slidingWindowSize).intValue() < report.get(i).intValue())
                .count();
    }
}
 