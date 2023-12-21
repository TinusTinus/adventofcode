package nl.mvdr.adventofcode.adventofcode2023.day21;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.Solver;

/**
 * Solution to <a href="https://adventofcode.com/2023/day/21">?</a>.
 *
 * @author Martijn van de Rijdt
 */
public class StepCounterPart2 implements Solver {

    private static final Logger LOGGER = LoggerFactory.getLogger(StepCounterPart2.class);

    private final int steps;
    
    /**
     * Default constructor.
     */
    public StepCounterPart2() {
        this(26501365);
    }
    
    /**
     * Constructor.
     * 
     * @param steps the elf's remaining number of steps for the day
     */
    StepCounterPart2(int steps) {
        super();
        this.steps = steps;
    }
    
    @Override
    public String solve(String inputfile) {
        return new StepCounter(true, steps).solve(inputfile);
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        var instance = new StepCounterPart2();

        var result = instance.solve("input-day21-2023.txt");

        LOGGER.info(result);
    }
}
 