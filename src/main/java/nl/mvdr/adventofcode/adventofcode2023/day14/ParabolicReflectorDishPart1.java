package nl.mvdr.adventofcode.adventofcode2023.day14;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.IntSolver;

/**
 * Solution to <a href="https://adventofcode.com/2023/day/14">Parabolic Reflector Dish</a>.
 *
 * @author Martijn van de Rijdt
 */
public class ParabolicReflectorDishPart1 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(ParabolicReflectorDishPart1.class);

    @Override
    public int solve(Stream<String> lines) {
        var platform = Platform.parse(lines.toList());
        
        return 0; // TODO
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        var instance = new ParabolicReflectorDishPart1();

        var result = instance.solve("input-day14-2023.txt");

        LOGGER.info(result);
    }
}
 