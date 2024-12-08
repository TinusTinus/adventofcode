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
public class TrebuchetPart2 implements Solver {

    private static final Logger LOGGER = LoggerFactory.getLogger(TrebuchetPart2.class);

    @Override
    public String solve(String inputfile) {
        return new Trebuchet(true).solve(inputfile);
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        TrebuchetPart2 instance = new TrebuchetPart2();

        String result = instance.solve("input-day01-2023.txt");

        LOGGER.info(result);
    }
}
 