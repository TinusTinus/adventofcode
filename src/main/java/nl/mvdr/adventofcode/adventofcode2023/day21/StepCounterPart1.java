package nl.mvdr.adventofcode.adventofcode2023.day21;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.IntSolver;

/**
 * Solution to <a href="https://adventofcode.com/2023/day/21">?</a>.
 *
 * @author Martijn van de Rijdt
 */
public class StepCounterPart1 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(StepCounterPart1.class);

    private final int steps;
    
    /**
     * Default constructor.
     */
    public StepCounterPart1() {
        this(64);
    }
    
    /**
     * Constructor.
     * 
     * @param steps the elf's remaining number of steps for the day
     */
    StepCounterPart1(int steps) {
        super();
        this.steps = steps;
    }
    
    @Override
    public int solve(Stream<String> lines) {
        var map = GardenMap.parse(lines.toList());
        
        return 0; // TODO
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        var instance = new StepCounterPart1();

        var result = instance.solve("input-day21-2023.txt");

        LOGGER.info(result);
    }
}
 