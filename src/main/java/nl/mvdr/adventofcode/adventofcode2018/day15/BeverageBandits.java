package nl.mvdr.adventofcode.adventofcode2018.day15;

import java.io.IOException;
import java.nio.file.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.PathSolver;

/**
 * Solution to the day 15 puzzle of 2018's Advent of Code:
 * <a href="https://adventofcode.com/2018/day/15">Beverage Bandits</a>.
 *
 * @author Martijn van de Rijdt
 */
public class BeverageBandits implements PathSolver {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(BeverageBandits.class);
    
    @Override
    public String solve(Path inputFilePath) throws IOException {
        State state = State.parse(inputFilePath);
        
        return null;
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        BeverageBandits solver = new BeverageBandits();
        String solution = solver.solve("input-day15-2018.txt");
        LOGGER.info(solution);
    }
}
