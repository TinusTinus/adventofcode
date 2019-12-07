package nl.mvdr.adventofcode.adventofcode2019.day07;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Solution to the day 7 puzzle of 2019's Advent of Code:
 * <a href="https://adventofcode.com/2019/day/6">Amplification Circuit</a>.
 *
 * @author Martijn van de Rijdt
 */
public class AmplificationCircuitPart2 extends AmplificationCircuit {

    private static final Logger LOGGER = LoggerFactory.getLogger(AmplificationCircuitPart2.class);
    
    /** Constructor. */
    public AmplificationCircuitPart2() {
        super(5, 10);
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        AmplificationCircuitPart2 instance = new AmplificationCircuitPart2();

        String result = instance.solve("input-day07-2019.txt");

        LOGGER.info(result);
    }
}
