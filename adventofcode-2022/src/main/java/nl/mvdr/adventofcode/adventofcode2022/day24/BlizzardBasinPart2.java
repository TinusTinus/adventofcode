package nl.mvdr.adventofcode.adventofcode2022.day24;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.solver.IntSolver;

/**
 * Solution to <a href="https://adventofcode.com/2022/day/24">Blizzard Basin</a>.
 *
 * @author Martijn van de Rijdt
 */
public class BlizzardBasinPart2 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(BlizzardBasinPart2.class);

    @Override
    public int solve(Stream<String> lines) {
        var basin = Basin.parse(lines.toList());
        return basin.moveToGoalStartGoal().minutesPassed();
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        var instance = new BlizzardBasinPart2();

        var result = instance.solve("input-day24-2022.txt");

        LOGGER.info(result);
    }
}
 