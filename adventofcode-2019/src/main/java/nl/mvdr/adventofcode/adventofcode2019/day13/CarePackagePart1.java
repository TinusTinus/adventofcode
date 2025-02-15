package nl.mvdr.adventofcode.adventofcode2019.day13;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.solver.LongSolver;
import nl.mvdr.adventofcode.adventofcode2019.intcode.Program;

/**
 * Solution to the day 13 puzzle of 2019's Advent of Code:
 * <a href="https://adventofcode.com/2019/day/13">Care Package</a>.
 *
 * @author Martijn van de Rijdt
 */
public class CarePackagePart1 implements LongSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(CarePackagePart1.class);
    
    /**
     * {@inheritDoc}
     * 
     * @return how many block tiles are on the screen when the game exits
     */
    @Override
    public long solve(Stream<String> lines) {
        GameState gameState = new GameState();
        
        Program.parse(lines.findFirst().orElseThrow())
            .withOutput(gameState::process)
            .execute();
        
        return gameState.countBlocks();
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        CarePackagePart1 instance = new CarePackagePart1();

        String result = instance.solve("input-day13-2019.txt");

        LOGGER.info(result);
    }
}
