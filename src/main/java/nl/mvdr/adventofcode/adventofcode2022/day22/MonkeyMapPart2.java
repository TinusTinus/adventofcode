package nl.mvdr.adventofcode.adventofcode2022.day22;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.solver.Solver;

/**
 * Solution to <a href="https://adventofcode.com/2022/day/22">Monkey Map</a>.
 *
 * @author Martijn van de Rijdt
 */
public class MonkeyMapPart2 implements Solver {

    private static final Logger LOGGER = LoggerFactory.getLogger(MonkeyMapPart2.class);

    @Override
    public String solve(String inputfile) {
        return new MonkeyMap(WrapAroundStrategy.CUBE).solve(inputfile);
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        var instance = new MonkeyMapPart2();

        var result = instance.solve("input-day22-2022.txt");

        LOGGER.info(result);
    }
}
 