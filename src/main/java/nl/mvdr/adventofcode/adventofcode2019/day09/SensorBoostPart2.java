package nl.mvdr.adventofcode.adventofcode2019.day09;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Solution to the day 9 puzzle of 2019's Advent of Code:
 * <a href="https://adventofcode.com/2019/day/9">Sensor Boost</a>.
 *
 * @author Martijn van de Rijdt
 */
public class SensorBoostPart2 extends SensorBoost {

    private static final Logger LOGGER = LoggerFactory.getLogger(SensorBoostPart2.class);
    
    /** Constructor. */
    public SensorBoostPart2() {
        super(2L);
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        SensorBoostPart2 instance = new SensorBoostPart2();

        String result = instance.solve("input-day09-2019.txt");

        LOGGER.info(result);
    }
}
