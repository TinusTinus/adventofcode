package nl.mvdr.adventofcode.adventofcode2019.day22;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.LongSolver;

/**
 * Solution to the day 22 puzzle of 2019's Advent of Code:
 * <a href="https://adventofcode.com/2019/day/22">Slam Shuffle</a>.
 *
 * @author Martijn van de Rijdt
 */
public class SlamShufflePart2 implements LongSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(SlamShufflePart2.class);

    /**
     * {@inheritDoc}
     * 
     * @return the number on the card that ends up in position 2020,
     *     after shuffling 119315717514047 space cards 101741582076661 times
     */
    @Override
    public long solve(Stream<String> lines) {
        Process process = Process.parse(lines);
        
        long result = 2020L;
        // TODO see if we can discover a repeating pattern instead of performing the entire computation
        for (long i = 0L; i != 101741582076661L; i++) {
            result = process.computePreviousIndex(result, 119315717514047L);
            
            LOGGER.debug("Result after shuffling {} times: {}", Long.valueOf(i), Long.valueOf(result));
        }
        
        return result;
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        SlamShufflePart2 instance = new SlamShufflePart2();

        String result = instance.solve("input-day22-2019.txt");

        LOGGER.info(result);
    }
}
 