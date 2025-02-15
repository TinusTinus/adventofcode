package nl.mvdr.adventofcode.adventofcode2020.day12;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Solution to the day 12 puzzle of 2020's Advent of Code:
 * <a href="https://adventofcode.com/2020/day/12">Rain Risk</a>.
 *
 * @author Martijn van de Rijdt
 */
public class RainRiskPart1 extends RainRisk {

    private static final Logger LOGGER = LoggerFactory.getLogger(RainRiskPart1.class);

    /** Constructor. */
    public RainRiskPart1() {
        super(true);
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        RainRiskPart1 instance = new RainRiskPart1();

        String result = instance.solve("input-day12-2020.txt");

        LOGGER.info(result);
    }
}
 