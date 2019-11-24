package nl.mvdr.adventofcode.adventofcode2018.day15;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.IntSolver;

/**
 * Solution to the day 15 puzzle of 2018's Advent of Code:
 * <a href="https://adventofcode.com/2018/day/15">Beverage Bandits</a>.
 *
 * @author Martijn van de Rijdt
 */
public class BeverageBanditsPart1 implements IntSolver {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(BeverageBanditsPart1.class);
    
    @Override
    public int solve(Stream<String> lines) {
        State initialState = State.parse(lines);
        
        State endState = initialState.performCombat(false);
        
        return endState.getOutcome();
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
