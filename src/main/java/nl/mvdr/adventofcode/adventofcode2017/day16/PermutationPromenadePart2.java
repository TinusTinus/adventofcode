package nl.mvdr.adventofcode.adventofcode2017.day16;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Solution to the day 16 puzzle of 2017's Advent of Code:
 * <a href="https://adventofcode.com/2017/day/16">Permutation Promenade</a>.
 *
 * @author Martijn van de Rijdt
 */
public class PermutationPromenadePart2 extends PermutationPromenade {

    private static final Logger LOGGER = LoggerFactory.getLogger(PermutationPromenadePart2.class);

    /** Constructor. */
    public PermutationPromenadePart2() {
        // The resulting dances loop.
        // The result at 1_000_000_000 is the same as the one at 10, 40, 70, 100 etc. (at least for my input).
        super(10, 16);
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        PermutationPromenadePart2 instance = new PermutationPromenadePart2();

        String result = instance.solve("input-day16-2017.txt");

        LOGGER.info(result);
    }
}
