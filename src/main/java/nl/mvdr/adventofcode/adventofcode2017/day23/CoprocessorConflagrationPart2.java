package nl.mvdr.adventofcode.adventofcode2017.day23;

import nl.mvdr.adventofcode.LongSolver;
import org.apache.commons.math3.primes.Primes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Solution to the day 23 puzzle of 2017's Advent of Code:
 * <a href="https://adventofcode.com/2017/day/23">Coprocessor Conflagration</a>.
 *
 * @author Martijn van de Rijdt
 */
public class CoprocessorConflagrationPart2 implements LongSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(CoprocessorConflagrationPart2.class);
    
    /**
     * {@inheritDoc}
     * 
     * @return the value in register h after execution
     */
    @Override
    public long solve(Stream<String> lines) {
        int b = 65; // determined by analyzing the specific puzzle input!
        return IntStream.range(0, 1001)
                .map(i -> b * 100 + 100000 + i * 17)
                .filter(i -> !Primes.isPrime(i))
                .count();
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        CoprocessorConflagrationPart2 instance = new CoprocessorConflagrationPart2();

        String result = instance.solve("input-day23-2017.txt");

        LOGGER.info(result);
    }
}
