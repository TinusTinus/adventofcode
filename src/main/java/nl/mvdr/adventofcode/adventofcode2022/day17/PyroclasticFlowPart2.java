package nl.mvdr.adventofcode.adventofcode2022.day17;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.solver.LongSolver;

/**
 * Solution to <a href="https://adventofcode.com/2022/day/17">Pyroclastic Flow</a>.
 *
 * @author Martijn van de Rijdt
 */
public class PyroclasticFlowPart2 implements LongSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(PyroclasticFlowPart2.class);

    private static final long ROCKS = 1_000_000_000_000L;
    
    @Override
    public long solve(Stream<String> lines) {
        String jetStreamString = lines.findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No input provided."));
        var chamber = Chamber.initialize(jetStreamString);
        var cycle = chamber.simulateUntilRepeats();
        
        long remainingRocks = ROCKS - chamber.getSettledRockCount();
        
        long cycleOccurrences = remainingRocks / cycle.getRocksInCycle();
        
        long remainingRocksToSettle = remainingRocks % cycle.getRocksInCycle();
        chamber.simulateUntil(chamber.getSettledRockCount() + (int) remainingRocksToSettle);
        
        return chamber.height() + cycleOccurrences * cycle.getCycleHeight();
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
 