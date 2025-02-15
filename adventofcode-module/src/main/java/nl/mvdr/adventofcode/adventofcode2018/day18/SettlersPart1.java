package nl.mvdr.adventofcode.adventofcode2018.day18;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Solution to the day 18 puzzle of 2018's Advent of Code:
 * <a href="https://adventofcode.com/2018/day/18">Settlers of the North Pole</a>.
 *
 * @author Martijn van de Rijdt
 */
public class SettlersPart1 extends Settlers {

    private static final Logger LOGGER = LoggerFactory.getLogger(SettlersPart1.class);
    
    /** Constructor. */
    public SettlersPart1() {
        super(10);
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        SettlersPart1 instance = new SettlersPart1();

        String result = instance.solve("input-day18-2018.txt");

        LOGGER.info(result);
    }
}
