package nl.mvdr.adventofcode.adventofcode2021.day05;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.solver.Solver;

/**
 * Solution to <a href="https://adventofcode.com/2021/day/5">Hydrothermal Venture</a>.
 *
 * @author Martijn van de Rijdt
 */
public class HydrothermalVenturePart2 implements Solver {

    private static final Logger LOGGER = LoggerFactory.getLogger(HydrothermalVenturePart2.class);

    @Override
    public String solve(String inputfile) {
        return new HydrothermalVenture(true).solve(inputfile);
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        var instance = new HydrothermalVenturePart2();

        var result = instance.solve("input-day05-2021.txt");

        LOGGER.info(result);
    }
}
 