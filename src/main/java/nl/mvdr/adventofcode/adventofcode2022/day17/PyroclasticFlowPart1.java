package nl.mvdr.adventofcode.adventofcode2022.day17;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.IntSolver;

/**
 * Solution to <a href="https://adventofcode.com/2022/day/17">Pyroclastic Flow</a>.
 *
 * @author Martijn van de Rijdt
 */
public class PyroclasticFlowPart1 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(PyroclasticFlowPart1.class);

    @Override
    public int solve(Stream<String> lines) {
        var chamber = Chamber.initialize(lines);
        
        while (chamber.getSettledRockCount() < 2023) {
            chamber.tick();
        }
        
        return chamber.height();
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        var instance = new PyroclasticFlowPart1();

        var result = instance.solve("input-day17-2022.txt");

        LOGGER.info(result);
    }
}
 