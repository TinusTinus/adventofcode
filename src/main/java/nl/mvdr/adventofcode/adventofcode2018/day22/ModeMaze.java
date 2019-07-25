package nl.mvdr.adventofcode.adventofcode2018.day22;

import java.io.IOException;
import java.nio.file.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.PathSolver;

/**
 * Solution to the day 22 puzzle of 2018's Advent of Code:
 * <a href="https://adventofcode.com/2018/day/22">Mode Maze</a>.
 *
 * @author Martijn van de Rijdt
 */
public class ModeMaze implements PathSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(ModeMaze.class);

    @Override
    public String solve(Path inputFilePath) throws IOException {
        Cave cave = Cave.parse(inputFilePath);
        return "" + cave.getTotalRiskLevel();
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        ModeMaze solver = new ModeMaze();
        String solution = solver.solve("input-day22-2018.txt");
        LOGGER.info(solution);
    }
}
