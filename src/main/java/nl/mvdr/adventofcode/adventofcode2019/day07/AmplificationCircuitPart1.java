package nl.mvdr.adventofcode.adventofcode2019.day07;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Solution to the day 7 puzzle of 2019's Advent of Code:
 * <a href="https://adventofcode.com/2019/day/6">Amplification Circuit</a>.
 *
 * @author Martijn van de Rijdt
 */
public class AmplificationCircuitPart1 extends AmplificationCircuit {

    private static final Logger LOGGER = LoggerFactory.getLogger(AmplificationCircuitPart1.class);
    
    /** Constructor. */
    public AmplificationCircuitPart1() {
        super(0, 5);
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        AmplificationCircuitPart1 instance = new AmplificationCircuitPart1();

        String result = instance.solve("input-day07-2019.txt");

        LOGGER.info(result);
    }
}
