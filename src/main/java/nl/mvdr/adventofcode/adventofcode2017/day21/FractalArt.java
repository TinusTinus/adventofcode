package nl.mvdr.adventofcode.adventofcode2017.day21;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.PathSolver;

/**
 * Solution to the day 21 puzzle of 2017's Advent of Code:
 * <a href="https://adventofcode.com/2017/day/21">Fractal Art</a>.
 *
 * @author Martijn van de Rijdt
 */
public class FractalArt implements PathSolver<Long> {

    private static final Logger LOGGER = LoggerFactory.getLogger(FractalArt.class);
    
    private final int iterations;
    
    /**
     * Constructor.
     * 
     * @param iterations number of iterations to perform
     */
    FractalArt(int iterations) {
        super();
        this.iterations = iterations;
    }
    
    /**
     * {@inheritDoc}
     * 
     * @return the number of pixels which are on
     */
    @Override
    public Long solve(Path inputFilePath) throws IOException {
        Set<EnhancementRule> rules = EnhancementRule.parse(inputFilePath);
        
        Image image = Image.INITIAL_IMAGE.enhance(iterations, rules);
        
        return Long.valueOf(image.countOnPixels());
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        FractalArt part1 = new FractalArt(5);
        String part1Result = part1.solve("input-day21-2017.txt");
        LOGGER.info("Part 1 result: {}", part1Result);
        
        FractalArt part2 = new FractalArt(18);
        String part2Result = part2.solve("input-day21-2017.txt");
        LOGGER.info("Part 2 result: {}", part2Result);
    }
}
