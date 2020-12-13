package nl.mvdr.adventofcode.adventofcode2020.day09;

import java.util.Arrays;
import java.util.function.Predicate;
import java.util.stream.IntStream;
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
public class EncodingErrorPart1 implements LongSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(EncodingErrorPart1.class);

    private final int preambleSize;

    /**
     * Constructor.
     * 
     * @param preambleSize size of the preamble,
     * as well as the number of integers before the current number to search for pairs
     */
    EncodingErrorPart1(int preambleSize) {
        super();
        this.preambleSize = preambleSize;
    }
    
    /** Constructor. */
    public EncodingErrorPart1() {
        this(25);
    }
    
    /**
     * {@inheritDoc}
     * 
     * @return the first number which is not the sum of two of the 25 numbers before it
     */
    @Override
    public long solve(Stream<String> lines) {
        long[] numbers = lines.filter(Predicate.not(String::isEmpty))
                .mapToLong(Long::parseLong)
                .toArray();
        
        return IntStream.range(preambleSize, numbers.length)
                .filter(i -> Pair.findPairWhichSumsTo(Arrays.copyOfRange(numbers, i - preambleSize, i), numbers[i]).isEmpty())
                .mapToLong(i -> numbers[i])
                .findFirst()
                .orElseThrow();
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        EncodingErrorPart1 instance = new EncodingErrorPart1();

        String result = instance.solve("input-day09-2020.txt");

        LOGGER.info(result);
    }
}
 