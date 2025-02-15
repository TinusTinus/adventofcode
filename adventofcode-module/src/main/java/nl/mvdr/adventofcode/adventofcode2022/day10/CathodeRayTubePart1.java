package nl.mvdr.adventofcode.adventofcode2022.day10;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.solver.IntSolver;

/**
 * Solution to <a href="https://adventofcode.com/2022/day/10">Cathode-Ray Tube</a>.
 *
 * @author Martijn van de Rijdt
 */
public class CathodeRayTubePart1 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(CathodeRayTubePart1.class);
    
    @Override
    public int solve(Stream<String> lines) {
        ProgramResult result = ProgramResult.get(lines);
        return result.signalStrengthSum();
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        var instance = new CathodeRayTubePart1();

        var result = instance.solve("input-day10-2022.txt");

        LOGGER.info(result);
    }
}
 