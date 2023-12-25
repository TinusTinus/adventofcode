package nl.mvdr.adventofcode.adventofcode2023.day25;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.IntSolver;

/**
 * Solution to <a href="https://adventofcode.com/2023/day/25">Snowverload</a>.
 *
 * @author Martijn van de Rijdt
 */
public class SnowverloadPart1 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(SnowverloadPart1.class);

    @Override
    public int solve(Stream<String> linesStream) {
        return 0; // TODO
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        var instance = new SnowverloadPart1();

        var result = instance.solve("input-day25-2023.txt");

        LOGGER.info(result);
    }
}
 