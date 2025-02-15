package nl.mvdr.adventofcode.adventofcode2018.day07;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Solution to part 1 of the day 7 puzzle of 2018's Advent of Code:
 * <a href="https://adventofcode.com/2018/day/7">The Sum of Its Parts</a>.
 *
 * @author Martijn van de Rijdt
 */
public class SumOfItsPartsPart1 extends SumOfItsParts {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(SumOfItsPartsPart1.class);
    
    /** Constructor. */
    public SumOfItsPartsPart1() {
        super(SumOfItsPartsSolution.STEPS, 0, 1);
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        SumOfItsPartsPart1 instance = new SumOfItsPartsPart1();

        String result = instance.solve("input-day07-2018.txt");

        LOGGER.info(result);
        
    }
}
