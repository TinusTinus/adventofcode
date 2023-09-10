package nl.mvdr.adventofcode.adventofcode2022.day22;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.IntSolver;

/**
 * Solution to <a href="https://adventofcode.com/2022/day/22">Monkey Map</a>.
 *
 * @author Martijn van de Rijdt
 */
public class MonkeyMapPart1 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(MonkeyMapPart1.class);

    @Override
    public int solve(Stream<String> lines) {
        return 0; // TODO
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        var instance = new MonkeyMapPart1();

        var result = instance.solve("input-day22-2022.txt");

        LOGGER.info(result);
    }
}
 