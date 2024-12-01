package nl.mvdr.adventofcode.adventofcode2016.day08;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.IntSolver;

/**
 * Solution to the day 8 puzzle of 2016's Advent of Code:
 * <a href="https://adventofcode.com/2016/day/8">Two-Factor Authentication</a>.
 *
 * @author Martijn van de Rijdt
 */
public class TwoFactorAuthentication implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(TwoFactorAuthentication.class);
    
    private final int width;
    private final int height;
    
    /** Constructor. */
    public TwoFactorAuthentication() {
        this(50, 6);
    }
    
    /**
     * Constructor.
     * 
     * @param width width of the screen
     * @param height height of the screen
     */
    TwoFactorAuthentication(int width, int height) {
        super();
        this.width = width;
        this.height = height;
    }
    
    /**
     * {@inheritDoc}
     * 
     * @return number of lit pixels
     */
    @Override
    public int solve(Stream<String> lines) {
        List<Instruction> instructions = lines.filter(Predicate.not(String::isBlank))
                .map(Instruction::parse)
                .toList();
        
        Screen screen = new Screen(width, height);
        
        for (Instruction instruction : instructions) {
            screen = instruction.execute(screen);
        }
        
        // Log the answer to part 2
        LOGGER.info("After executing {} instruction(s): {}", Integer.valueOf(instructions.size()), screen);
        
        // Return the answer to part 1
        return screen.litPixels();
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        TwoFactorAuthentication instance = new TwoFactorAuthentication();

        String result = instance.solve("input-day08-2016.txt");

        LOGGER.info(result);
    }

}
