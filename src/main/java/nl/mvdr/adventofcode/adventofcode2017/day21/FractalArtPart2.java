package nl.mvdr.adventofcode.adventofcode2017.day21;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.solver.Solver;

/**
 * Solution to the day 21 puzzle of 2017's Advent of Code:
 * <a href="https://adventofcode.com/2017/day/21">Fractal Art</a>.
 *
 * @author Martijn van de Rijdt
 */
public class FractalArtPart2 implements Solver {

    private static final Logger LOGGER = LoggerFactory.getLogger(FractalArtPart2.class);

    @Override
    public String solve(String inputfile) {
        return new FractalArt(18).solve(inputfile);
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        FractalArtPart2 part1 = new FractalArtPart2();
        String part1Result = part1.solve("input-day21-2017.txt");
        LOGGER.info("Result: {}", part1Result);
    }
}
