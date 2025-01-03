package nl.mvdr.adventofcode.adventofcode2022.day09;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.solver.IntSolver;

/**
 * Solution to <a href="https://adventofcode.com/2022/day/09">Rope Bridge</a>.
 *
 * @author Martijn van de Rijdt
 */
public class RopeBridgePart1 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(RopeBridgePart1.class);
    
    @Override
    public int solve(Stream<String> lines) {
        var solver = new RopeBridge(2);
        return solver.solve(lines);
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        var instance = new RopeBridgePart1();

        var result = instance.solve("input-day09-2022.txt");

        LOGGER.info(result);
    }
}
 