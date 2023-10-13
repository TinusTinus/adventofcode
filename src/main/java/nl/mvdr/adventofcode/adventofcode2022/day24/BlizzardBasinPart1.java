package nl.mvdr.adventofcode.adventofcode2022.day24;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.IntSolver;

/**
 * Solution to <a href="https://adventofcode.com/2022/day/24">Blizzard Basin</a>.
 *
 * @author Martijn van de Rijdt
 */
public class BlizzardBasinPart1 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(BlizzardBasinPart1.class);

    @Override
    public int solve(Stream<String> lines) {
        var basin = Basin.parse(lines.toList());
        return basin.shortestPathToGoal();
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        var instance = new BlizzardBasinPart1();

        var result = instance.solve("input-day24-2022.txt");

        LOGGER.info(result);
    }
}
 