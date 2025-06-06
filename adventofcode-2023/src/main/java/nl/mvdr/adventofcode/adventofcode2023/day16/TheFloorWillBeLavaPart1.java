package nl.mvdr.adventofcode.adventofcode2023.day16;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.solver.IntSolver;

/**
 * Solution to <a href="https://adventofcode.com/2023/day/16">The Floor Will Be Lava</a>.
 *
 * @author Martijn van de Rijdt
 */
public class TheFloorWillBeLavaPart1 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(TheFloorWillBeLavaPart1.class);

    @Override
    public int solve(Stream<String> lines) {
        var contraption = Contraption.parse(lines.toList());
        return contraption.energizedTiles();
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        var instance = new TheFloorWillBeLavaPart1();

        var result = instance.solve("input-day16-2023.txt");

        LOGGER.info(result);
    }
}
 