package nl.mvdr.adventofcode.adventofcode2017.day21;

import java.util.Set;
import java.util.stream.Stream;

import nl.mvdr.adventofcode.LongSolver;

/**
 * Solution to the day 21 puzzle of 2017's Advent of Code:
 * <a href="https://adventofcode.com/2017/day/21">Fractal Art</a>.
 *
 * @author Martijn van de Rijdt
 */
public class FractalArt implements LongSolver {

    private final int iterations;
    
    /**
     * Constructor.
     */
    public FractalArt() {
        this(5); // default value for part 1
    }
    
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
    public long solve(Stream<String> lines) {
        Set<EnhancementRule> rules = EnhancementRule.parse(lines);
        
        Image image = Image.INITIAL_IMAGE.enhance(iterations, rules);
        
        return image.countOnPixels();
    }
}
