package nl.mvdr.adventofcode.adventofcode2022.day17;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.LongSolver;

/**
 * Solution to <a href="https://adventofcode.com/2022/day/17">Pyroclastic Flow</a>.
 *
 * @author Martijn van de Rijdt
 */
public class PyroclasticFlowPart2 implements LongSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(PyroclasticFlowPart2.class);

    private static final int ROCKS = 2022;
    
    @Override
    public long solve(Stream<String> lines) {
        var chamber = Chamber.initialize(lines);
        
        // TODO stuff
        
        return chamber.height();
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        var instance = new PyroclasticFlowPart2();

        var result = instance.solve("input-day17-2022.txt");

        LOGGER.info(result);
    }
}
 