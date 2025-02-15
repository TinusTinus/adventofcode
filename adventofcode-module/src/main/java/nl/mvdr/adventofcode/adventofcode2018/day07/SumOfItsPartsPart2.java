package nl.mvdr.adventofcode.adventofcode2018.day07;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Solution to part 2 of the day 7 puzzle of 2018's Advent of Code:
 * <a href="https://adventofcode.com/2018/day/7">The Sum of Its Parts</a>.
 *
 * @author Martijn van de Rijdt
 */
public class SumOfItsPartsPart2 extends SumOfItsParts {
    private static final Logger LOGGER = LoggerFactory.getLogger(SumOfItsPartsPart2.class);
    
    /** Constructor. */
    public SumOfItsPartsPart2() {
        // parameters from the example
        this(0, 2);
    }
    
    /**
     * Constructor.
     * 
     * @param baseTime basic number of seconds needed to complete any step (0 in the example, 60 in the actual puzzle input)
     * @param workers number of parallel workers
     */
    private SumOfItsPartsPart2(int baseTime, int workers) {
        super(SumOfItsPartsSolution.TIME, baseTime, workers);
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        SumOfItsPartsPart2 instance = new SumOfItsPartsPart2(60, 5);

        String result = instance.solve("input-day07-2018.txt");

        LOGGER.info(result);
    }
}
