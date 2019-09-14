package nl.mvdr.adventofcode.adventofcode2017.day17;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Solution to the day 17 puzzle of 2017's Advent of Code:
 * <a href="https://adventofcode.com/2017/day/17">Spinlock</a>.
 *
 * @author Martijn van de Rijdt
 */
public class SpinlockPart1 extends Spinlock {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpinlockPart1.class);
    
    /** Constructor. */
    public SpinlockPart1() {
        super(2017);
    }
    
    @Override
    Integer solve(List<Integer> buffer) {
        return buffer.get(1);
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        SpinlockPart1 instance = new SpinlockPart1();

        String result = instance.solve("input-day17-2017.txt");

        LOGGER.info(result);
    }
}
