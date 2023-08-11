package nl.mvdr.adventofcode.adventofcode2022.day18;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Solution to <a href="https://adventofcode.com/2022/day/18">Boiling Boulders</a>.
 *
 * @author Martijn van de Rijdt
 */
public class BoilingBouldersPart2 extends BoilingBoulders {

    private static final Logger LOGGER = LoggerFactory.getLogger(BoilingBouldersPart2.class);

    /**
     * Constructor.
     */
    public BoilingBouldersPart2() {
        super(true);
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        var instance = new BoilingBouldersPart2();

        var result = instance.solve("input-day18-2022.txt");

        LOGGER.info(result);
    }
}
 