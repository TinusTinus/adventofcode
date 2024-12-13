package nl.mvdr.adventofcode.adventofcode2023.day13;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.solver.Solver;

/**
 * Solution to <a href="https://adventofcode.com/2023/day/13">Point of Incidence</a>.
 *
 * @author Martijn van de Rijdt
 */
public class PointOfIncidencePart1 implements Solver {

    private static final Logger LOGGER = LoggerFactory.getLogger(PointOfIncidencePart1.class);

    @Override
    public String solve(String inputfile) {
        return new PointOfIncidence(0).solve(inputfile);
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        var instance = new PointOfIncidencePart1();

        var result = instance.solve("input-day13-2023.txt");

        LOGGER.info(result);
    }
}
 