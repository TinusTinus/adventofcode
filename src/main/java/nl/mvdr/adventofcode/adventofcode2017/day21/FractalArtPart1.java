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
public class FractalArtPart1 implements PathSolver<Long> {

    private static final Logger LOGGER = LoggerFactory.getLogger(FractalArtPart1.class);
    
    private final int iterations;

    /** Constructor. */
    public FractalArtPart1() {
        this(5);
    }
    
    /**
     * Constructor.
     * 
     * @param iterations number of iterations to perform
     */
    FractalArtPart1(int iterations) {
        super();
        this.iterations = iterations;
    }
    
    /**
     * {@inheritDoc}
     * 
     * @return the particle which will stay closest to position <0,0,0> in the long term
     */
    @Override
    public Long solve(Path inputFilePath) throws IOException {
        Set<EnhancementRule> rules = EnhancementRule.parse(inputFilePath);
        
        Image image = Image.INITIAL_IMAGE;
        LOGGER.debug("Start {}", image);
        
        for (int iteration = 0; iteration != iterations; iteration++) {
            // TODO apply rules
            
            LOGGER.debug("{}", image);
        }
        
        return Long.valueOf(image.countOnPixels());
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        FractalArtPart1 instance = new FractalArtPart1();

        String result = instance.solve("input-day21-2017.txt");

        LOGGER.info(result);
    }
}
