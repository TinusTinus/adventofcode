package nl.mvdr.adventofcode.adventofcode2018.day16;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.LongSolver;

/**
 * Solution to the day 16 puzzle of 2018's Advent of Code:
 * <a href="https://adventofcode.com/2018/day/16">Chronal Classification</a>.
 *
 * @author Martijn van de Rijdt
 */
public class ChronalClassificationPart1 implements LongSolver {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ChronalClassificationPart1.class);
    
    @Override
    public long solve(Stream<String> lines) {
        PuzzleInput input = PuzzleInput.parse(lines);
        
        return input.getSamples().stream()
                .filter(Sample::behavesLikeAtLeastThreeOpcodes)
                .count();
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
