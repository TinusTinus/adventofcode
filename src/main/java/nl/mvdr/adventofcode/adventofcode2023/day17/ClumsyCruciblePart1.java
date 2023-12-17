package nl.mvdr.adventofcode.adventofcode2023.day17;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.IntSolver;

/**
 * Solution to <a href="https://adventofcode.com/2023/day/17">Clumsy Crucible</a>.
 *
 * @author Martijn van de Rijdt
 */
public class ClumsyCruciblePart1 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClumsyCruciblePart1.class);

    @Override
    public int solve(Stream<String> lines) {
        var city = City.parse(lines.toList());
        return 0; // TODO
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        var instance = new ClumsyCruciblePart1();

        var result = instance.solve("input-day17-2023.txt");

        LOGGER.info(result);
    }
}
 