package nl.mvdr.adventofcode.adventofcode2023.day22;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.IntSolver;

/**
 * Solution to <a href="https://adventofcode.com/2023/day/22">Sand Slabs</a>.
 *
 * @author Martijn van de Rijdt
 */
public class SandSlabsPart1 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(SandSlabsPart1.class);

    @Override
    public int solve(Stream<String> lines) {
        var bricks = lines.map(Brick::parse).toList();
        return 0; // TODO
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
 