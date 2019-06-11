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
public class BeverageBanditsPart1 implements PathSolver {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(BeverageBanditsPart1.class);
    
    @Override
    public String solve(Path inputFilePath) throws IOException {
        State initialState = State.parse(inputFilePath);
        
        return "" + initialState.performCombat(false).getOutcome();
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        BeverageBanditsPart1 solver = new BeverageBanditsPart1();
        String solution = solver.solve("input-day15-2018.txt");
        LOGGER.info(solution);
    }
}
