package nl.mvdr.adventofcode.adventofcode2021.day02;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.solver.Solver;

/**
 * Solution to 2021's Advent of Code:
 * <a href="https://adventofcode.com/2021/day/2">Dive!</a>.
 *
 * @author Martijn van de Rijdt
 */
public class DivePart1 implements Solver {

    private static final Logger LOGGER = LoggerFactory.getLogger(DivePart1.class);

    @Override
    public String solve(String inputfile) {
        return new Dive(false).solve(inputfile);
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        var instance = new DivePart1();

        var result = instance.solve("input-day02-2021.txt");

        LOGGER.info(result);
    }
}
