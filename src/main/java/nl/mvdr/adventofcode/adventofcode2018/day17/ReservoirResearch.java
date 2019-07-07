package nl.mvdr.adventofcode.adventofcode2018.day17;

import java.io.IOException;
import java.nio.file.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.PathSolver;

/**
 * Solution to the day 16 puzzle of 2018's Advent of Code:
 * <a href="https://adventofcode.com/2018/day/17">Reservoir Research</a>.
 *
 * @author Martijn van de Rijdt
 */
public class ReservoirResearch implements PathSolver {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ReservoirResearch.class);
    
    @Override
    public String solve(Path inputFilePath) throws IOException {
        return null; // TODO
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        ReservoirResearch solver = new ReservoirResearch();
        String solution = solver.solve("input-day17-2018.txt");
        LOGGER.info(solution);
    }
}
