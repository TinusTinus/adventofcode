package nl.mvdr.adventofcode.adventofcode2021.day01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.Solver;

/**
 * Solution to the day 1 puzzle of 2021's Advent of Code:
 * <a href="https://adventofcode.com/2021/day/1">Sonar Sweep</a>.
 *
 * @author Martijn van de Rijdt
 */
public class SonarSweepPart2 implements Solver {

    private static final Logger LOGGER = LoggerFactory.getLogger(SonarSweepPart2.class);

    @Override
    public String solve(String inputfile) {
        return new SonarSweep(3).solve(inputfile);
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        var instance = new SonarSweepPart2();

        var result = instance.solve("input-day01-2021.txt");

        LOGGER.info(result);
    }
}
 