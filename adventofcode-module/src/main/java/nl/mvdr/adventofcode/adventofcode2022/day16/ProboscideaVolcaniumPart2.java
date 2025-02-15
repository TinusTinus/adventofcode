package nl.mvdr.adventofcode.adventofcode2022.day16;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Solution to <a href="https://adventofcode.com/2022/day/16">Proboscidea Volcanium</a>.
 *
 * @author Martijn van de Rijdt
 */
public class ProboscideaVolcaniumPart2 extends ProboscideaVolcanium {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProboscideaVolcaniumPart2.class);

    /** Constructor. */
    public ProboscideaVolcaniumPart2() {
        super(true);
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        var instance = new ProboscideaVolcaniumPart2();

        var result = instance.solve("input-day16-2022.txt");

        LOGGER.info(result);
    }
}
 