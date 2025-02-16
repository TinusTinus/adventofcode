package nl.mvdr.adventofcode.adventofcode2023.day01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.solver.Solver;

/**
 * Solution to the day 1 puzzle of 2023's Advent of Code:
 * <a href="https://adventofcode.com/2023/day/1">Trebuchet?!</a>.
 *
 * @author Martijn van de Rijdt
 */
public class TrebuchetPart1 implements Solver {

    private static final Logger LOGGER = LoggerFactory.getLogger(TrebuchetPart1.class);

    @Override
    public String solve(String inputfile) {
        return new Trebuchet(false).solve(inputfile);
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        TrebuchetPart1 instance = new TrebuchetPart1();

        String result = instance.solve("input-day01-2023.txt");

        LOGGER.info(result);
    }
}
 