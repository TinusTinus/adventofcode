package nl.mvdr.adventofcode.adventofcode2022.day14;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Solution to <a href="https://adventofcode.com/2022/day/14">Regolith Reservoir</a>.
 *
 * @author Martijn van de Rijdt
 */
public class RegolithReservoirPart1 extends RegolithReservoir {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegolithReservoirPart1.class);

    /** Constructor. */
    public RegolithReservoirPart1() {
        super(false);
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        var instance = new RegolithReservoirPart1();

        var result = instance.solve("input-day14-2022.txt");

        LOGGER.info(result);
    }
}
 