package nl.mvdr.adventofcode.adventofcode2017.day16;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Solution to the day 16 puzzle of 2017's Advent of Code:
 * <a href="https://adventofcode.com/2017/day/16">Permutation Promenade</a>.
 *
 * @author Martijn van de Rijdt
 */
public class PermutationPromenadePart1 extends PermutationPromenade {

    private static final Logger LOGGER = LoggerFactory.getLogger(PermutationPromenadePart1.class);
    
    /** Constructor. */
    public PermutationPromenadePart1() {
        this(16);
    }
    
    /**
     * Constructor.
     * 
     * @param numberOfPrograms number of programs performing the dance
     */
    PermutationPromenadePart1(int numberOfPrograms) {
        super(1, numberOfPrograms);
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        PermutationPromenadePart1 instance = new PermutationPromenadePart1();

        String result = instance.solve("input-day16-2017.txt");

        LOGGER.info(result);
    }
}
