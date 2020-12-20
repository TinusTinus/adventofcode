package nl.mvdr.adventofcode.adventofcode2017.day23;

import java.util.stream.Stream;

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
        
        // Assembly code translated into Java, and optimized using the modulo operator:
        @SuppressWarnings("unused")
        int a = 1, b = 0, c = 0, d = 0, e = 0, f = 0, g = 0, h = 0;
        b = 65; // Note: depends on input
        c = b;
        if (a != 0) {
            b = b * 100 + 100000;
            c = b + 17000;
        }
        do {
            f = 1;
            d = 2;
            e = 2;
            for (d = 2; d * d <= b; d++) { // check if b is a prime
                // the assembly doesn't have a % operator,
                // so it does 2 for loops with d and e and checks if d*e==b.
                if ((b % d == 0)) {
                    f = 0;
                    break;
                }
            }
            if (f == 0) // not a prime
                h++;
            g = b - c;
            b += 17;
        } while (g != 0); // stop when b==c (1000 iterations)

        return h;
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
