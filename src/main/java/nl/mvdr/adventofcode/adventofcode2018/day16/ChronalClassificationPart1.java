package nl.mvdr.adventofcode.adventofcode2018.day16;

import java.io.IOException;
import java.nio.file.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.PathSolver;

/**
 * Solution to the day 16 puzzle of 2018's Advent of Code:
 * <a href="https://adventofcode.com/2018/day/16">Chronal Classification</a>.
 *
 * @author Martijn van de Rijdt
 */
public class ChronalClassificationPart1 implements PathSolver {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ChronalClassificationPart1.class);
    
    @Override
    public String solve(Path inputFilePath) throws IOException {
        PuzzleInput input = PuzzleInput.parse(inputFilePath);
        
        return input.getSamples().stream()
                .filter(Sample::behavesLikeAtLeastThreeOpcodes)
                .count() + "";
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        ChronalClassificationPart1 solver = new ChronalClassificationPart1();
        String solution = solver.solve("input-day16-2018.txt");
        LOGGER.info(solution);
    }
}
