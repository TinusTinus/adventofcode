package nl.mvdr.adventofcode.adventofcode2016.day09;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Solution to the day 9 puzzle of 2016's Advent of Code:
 * <a href="https://adventofcode.com/2016/day/9">Explosives in Cyberspace</a>.
 *
 * @author Martijn van de Rijdt
 */
public class ExplosivesInCyberspacePart1 extends ExplosivesInCyberspace {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExplosivesInCyberspacePart1.class);
    
    /** Constructor. */
    public ExplosivesInCyberspacePart1() {
        super(false);
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        ExplosivesInCyberspacePart1 instance = new ExplosivesInCyberspacePart1();

        String result = instance.solve("input-day09.txt");

        LOGGER.info(result);
    }
}
