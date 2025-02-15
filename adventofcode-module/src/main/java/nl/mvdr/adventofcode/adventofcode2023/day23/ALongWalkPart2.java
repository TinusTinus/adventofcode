package nl.mvdr.adventofcode.adventofcode2023.day23;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.solver.Solver;

/**
 * Solution to <a href="https://adventofcode.com/2023/day/23">A Long Walk</a>.
 *
 * @author Martijn van de Rijdt
 */
public class ALongWalkPart2 implements Solver {

    private static final Logger LOGGER = LoggerFactory.getLogger(ALongWalkPart2.class);

    @Override
    public String solve(String inputfile) {
        return new ALongWalk(false).solve(inputfile);
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        var instance = new ALongWalkPart2();

        var result = instance.solve("input-day23-2023.txt");

        LOGGER.info(result);
    }
}
 