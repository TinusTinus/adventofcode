package nl.mvdr.adventofcode.adventofcode2018.day14;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.PathSolver;

/**
 * Solution to the day 14 puzzle of 2018's Advent of Code:
 * <a href="https://adventofcode.com/2018/day/14">Chocolate Charts</a>.
 *
 * @author Martijn van de Rijdt
 */
public class ChocolateCharts implements PathSolver {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ChocolateCharts.class);
    
    @Override
    public String solve(Path inputFilePath) throws IOException {
        String inputString = Files.lines(inputFilePath)
                .findFirst()
                .get();
        int input = Integer.parseInt(inputString);
        return solve(input);
    }
    
    /**
     * Solver method
     * 
     * @param input input number
     * @return solution to the puzzle for the given input
     */
    private String solve(int input) {
        return null; // TODO
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        ChocolateCharts solver = new ChocolateCharts();
        String solution = solver.solve("input-day14-2018.txt");
        LOGGER.info(solution);
    }
}
