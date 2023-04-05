package nl.mvdr.adventofcode.adventofcode2022.day10;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.LinesSolver;

/**
 * Solution to <a href="https://adventofcode.com/2022/day/10">Cathode-Ray Tube</a>.
 *
 * @author Martijn van de Rijdt
 */
public class CathodeRayTubePart2 implements LinesSolver<String> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CathodeRayTubePart2.class);
    
    @Override
    public String solve(Stream<String> lines) {
        ProgramResult result = ProgramResult.get(lines);
        return result.image();
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        var instance = new CathodeRayTubePart2();

        var result = instance.solve("input-day10-2022.txt");

        LOGGER.info("\n" + result);
    }
}
 