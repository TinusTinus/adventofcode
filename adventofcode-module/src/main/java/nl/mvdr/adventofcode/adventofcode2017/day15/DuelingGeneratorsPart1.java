package nl.mvdr.adventofcode.adventofcode2017.day15;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Solution to the day 15 puzzle of 2017's Advent of Code:
 * <a href="https://adventofcode.com/2017/day/15">Dueling Generators</a>.
 *
 * @author Martijn van de Rijdt
 */
public class DuelingGeneratorsPart1 extends DuelingGenerators {

    private static final Logger LOGGER = LoggerFactory.getLogger(DuelingGeneratorsPart1.class);

    /** Constructor. */
    public DuelingGeneratorsPart1() {
        super(false);
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        DuelingGeneratorsPart1 instance = new DuelingGeneratorsPart1();

        String result = instance.solve("input-day15-2017.txt");

        LOGGER.info(result);
    }
}
