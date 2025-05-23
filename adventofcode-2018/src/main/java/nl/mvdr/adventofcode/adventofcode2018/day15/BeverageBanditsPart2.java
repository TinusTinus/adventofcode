package nl.mvdr.adventofcode.adventofcode2018.day15;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.solver.IntSolver;

/**
 * Solution to the day 15 puzzle of 2018's Advent of Code:
 * <a href="https://adventofcode.com/2018/day/15">Beverage Bandits</a>.
 *
 * @author Martijn van de Rijdt
 */
public class BeverageBanditsPart2 implements IntSolver {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(BeverageBanditsPart2.class);
    
    @Override
    public int solve(Stream<String> lines) {
        State initialState = State.parse(lines);
        
        LOGGER.debug("Initial state:\n{}", initialState);
        
        // Linear search for the lowest attack power which lets all of the elves survive.
        int i = State.DEFAULT_ATTACK_POWER + 1;
        State state = initialState.withElfAttackPower(i).performCombat(true);
        while(state.getElfDeaths() != 0) {
            i++;
            state = initialState.withElfAttackPower(i).performCombat(true);
        }
        
        LOGGER.debug("End state, with elf attack power {}:\n{}", Integer.valueOf(i), state);
        
        return state.getOutcome();
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        BeverageBanditsPart2 solver = new BeverageBanditsPart2();
        String solution = solver.solve("input-day15-2018.txt");
        LOGGER.info(solution);
    }
}
