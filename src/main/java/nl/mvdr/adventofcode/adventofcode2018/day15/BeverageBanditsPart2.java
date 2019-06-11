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
public class BeverageBanditsPart2 implements PathSolver {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(BeverageBanditsPart2.class);
    
    @Override
    public String solve(Path inputFilePath) throws IOException {
        State initialState = State.parse(inputFilePath);
        
        // Binary search for the lowest attack power which lets the elves win.
        int lowerBound = State.DEFAULT_ATTACK_POWER; // From part 1: the goblins win in a fair fight.
        int upperBound = Unit.MAX_HIT_POINTS; // This means elves are oneshotting goblins. A higher attack power than this has no benefits.
        
        while (lowerBound < upperBound - 1) {
            int middle = Math.floorDiv(lowerBound + upperBound, 2);
            LOGGER.debug("{}, {}, {}", lowerBound, middle, upperBound);
            
            State state = initialState.withElfAttackPower(middle);
            // Note: this could be optimized by stopping computation after the first elf death.
            State endState = state.performCombat();
            if (endState.getElfDeaths() == 0) {
                upperBound = middle;
            } else {
                lowerBound = middle;
            }
        }
        
        LOGGER.info("{}, {}", lowerBound, upperBound);
        
        State state = initialState.withElfAttackPower(upperBound);
        State endState = state.performCombat();
        return "" + endState.getOutcome();
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
