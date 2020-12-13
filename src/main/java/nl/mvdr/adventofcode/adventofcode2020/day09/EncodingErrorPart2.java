package nl.mvdr.adventofcode.adventofcode2020.day09;

import java.util.function.Predicate;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.LongSolver;
import nl.mvdr.adventofcode.adventofcode2020.sums.Pair;

/**
 * Solution to the day 9 puzzle of 2020's Advent of Code:
 * <a href="https://adventofcode.com/2020/day/9">Encoding Error</a>.
 *
 * @author Martijn van de Rijdt
 */
public class EncodingErrorPart2 implements LongSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(EncodingErrorPart2.class);

    private final int preambleSize;

    /**
     * Constructor.
     * 
     * @param preambleSize size of the preamble,
     * as well as the number of integers before the current number to search for pairs
     */
    EncodingErrorPart2(int preambleSize) {
        super();
        this.preambleSize = preambleSize;
    }
    
    /** Constructor. */
    public EncodingErrorPart2() {
        this(25);
    }
    
    /**
     * {@inheritDoc}
     * 
     * @return encryption weakness
     */
    @Override
    public long solve(Stream<String> lines) {
        long[] numbers = lines.filter(Predicate.not(String::isEmpty))
                .mapToLong(Long::parseLong)
                .toArray();
        
        return solve(numbers);
    }

    /**
     * Solver method.
     * 
     * @param numbers input numbers
     * @return encryption weakness
     */
    private long solve(long[] numbers) {
        long invalidNumber = new EncodingErrorPart1(preambleSize).solve(numbers);
        
        return Pair.findContiguousSet(numbers, invalidNumber).sum();
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        EncodingErrorPart2 instance = new EncodingErrorPart2();

        String result = instance.solve("input-day09-2020.txt");

        LOGGER.info(result);
    }
}
 