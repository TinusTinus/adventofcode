package nl.mvdr.adventofcode.adventofcode2019.day13;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.LongSolver;
import nl.mvdr.adventofcode.adventofcode2019.intcode.Program;

/**
 * Solution to the day 13 puzzle of 2019's Advent of Code:
 * <a href="https://adventofcode.com/2019/day/13">Care Package</a>.
 *
 * @author Martijn van de Rijdt
 */
public class CarePackagePart2 implements LongSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(CarePackagePart2.class);
    
    /**
     * {@inheritDoc}
     * 
     * @return score
     */
    @Override
    public long solve(Stream<String> lines) {
        
        GameState gameState = new GameState();
        
        Program.parse(lines.findFirst().orElseThrow(), gameState::getInput, gameState::process)
                .set(0, 2)
                .execute();
        
        return gameState.getScore();
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        CarePackagePart2 instance = new CarePackagePart2();

        String result = instance.solve("input-day13-2019.txt");

        LOGGER.info(result);
    }
}
