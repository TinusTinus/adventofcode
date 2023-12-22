package nl.mvdr.adventofcode.adventofcode2023.day22;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.LongSolver;

/**
 * Solution to <a href="https://adventofcode.com/2023/day/22">Sand Slabs</a>.
 *
 * @author Martijn van de Rijdt
 */
public class SandSlabsPart1 implements LongSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(SandSlabsPart1.class);

    @Override
    public long solve(Stream<String> lines) {
        var bricks = lines.map(Brick::parse).collect(Collectors.toSet());
        var settledBricks = Brick.settle(bricks);
        
        return settledBricks.stream()
                .filter(brick -> brick.canBeDisintegrated(settledBricks))
                .count();
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        var instance = new SandSlabsPart1();

        var result = instance.solve("input-day22-2023.txt");

        LOGGER.info(result);
    }
}
 