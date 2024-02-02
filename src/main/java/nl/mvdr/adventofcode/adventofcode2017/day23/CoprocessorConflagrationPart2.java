package nl.mvdr.adventofcode.adventofcode2017.day23;

import java.util.stream.Stream;

import org.apache.commons.math3.primes.Primes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.IntSolver;

/**
 * Solution to the day 23 puzzle of 2017's Advent of Code:
 * <a href="https://adventofcode.com/2017/day/23">Coprocessor Conflagration</a>.
 *
 * @author Martijn van de Rijdt
 */
public class CoprocessorConflagrationPart2 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(CoprocessorConflagrationPart2.class);
    
    /**
     * {@inheritDoc}
     * 
     * @return the value in register h after execution
     */
    @Override
    public int solve(Stream<String> lines) {
        // Solution inspired by (a.k.a. ripped off from):
        //   https://www.reddit.com/r/adventofcode/comments/7lms6p/2017_day_23_solutions/drngj9r/
        // Assembly code translated into Java.
        int b = 65 * 100 + 100000; // Note: the value of 65 was determined by analyzing the specific puzzle input
        int result = 0;
        for (int i = 0; i != 1001; i++) {
            if (!Primes.isPrime(b)) {
                result++;
            }
            b += 17;
        }
        return result;
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
