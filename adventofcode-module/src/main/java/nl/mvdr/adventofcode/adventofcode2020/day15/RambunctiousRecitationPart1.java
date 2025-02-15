package nl.mvdr.adventofcode.adventofcode2020.day15;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Solution to the day 15 puzzle of 2020's Advent of Code:
 * <a href="https://adventofcode.com/2020/day/15">Rambunctious Recitation</a>.
 *
 * @author Martijn van de Rijdt
 */
public class RambunctiousRecitationPart1 extends RambunctiousRecitation {

    private static final Logger LOGGER = LoggerFactory.getLogger(RambunctiousRecitationPart1.class);

    /** Constructor. */
    public RambunctiousRecitationPart1() {
        super(2020);
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        RambunctiousRecitationPart1 instance = new RambunctiousRecitationPart1();

        String result = instance.solve("input-day15-2020.txt");

        LOGGER.info(result);
    }
}
 