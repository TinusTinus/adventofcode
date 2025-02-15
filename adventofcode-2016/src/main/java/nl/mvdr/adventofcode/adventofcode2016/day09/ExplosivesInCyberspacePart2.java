package nl.mvdr.adventofcode.adventofcode2016.day09;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Solution to the day 9 puzzle of 2016's Advent of Code:
 * <a href="https://adventofcode.com/2016/day/9">Explosives in Cyberspace</a>.
 *
 * @author Martijn van de Rijdt
 */
public class ExplosivesInCyberspacePart2 extends ExplosivesInCyberspace {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExplosivesInCyberspacePart2.class);
    
    /** Constructor. */
    public ExplosivesInCyberspacePart2() {
        super(true);
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        ExplosivesInCyberspacePart2 instance = new ExplosivesInCyberspacePart2();

        String result = instance.solve("input-day09-2016.txt");

        LOGGER.info(result);
    }
}
