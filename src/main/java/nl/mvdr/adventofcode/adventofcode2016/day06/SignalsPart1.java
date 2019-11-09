package nl.mvdr.adventofcode.adventofcode2016.day06;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Solution to the day 6 puzzle of 2016's Advent of Code:
 * <a href="https://adventofcode.com/2016/day/6">Signals and Noise</a>.
 *
 * @author Martijn van de Rijdt
 */
public class SignalsPart1 extends Signals {

    private static final Logger LOGGER = LoggerFactory.getLogger(SignalsPart1.class);
    
    /** Constructor. */
    public SignalsPart1() {
        super(true);
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        SignalsPart1 instance = new SignalsPart1();

        String result = instance.solve("input-day06-2016.txt");

        LOGGER.info(result);
    }
}
