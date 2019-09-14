package nl.mvdr.adventofcode.adventofcode2017.day17;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Solution to the day 17 puzzle of 2017's Advent of Code:
 * <a href="https://adventofcode.com/2017/day/17">Spinlock</a>.
 *
 * @author Martijn van de Rijdt
 */
public class SpinlockPart2 extends Spinlock {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpinlockPart2.class);
    
    @Override
    Integer solve(int stepSize) {
        // Value 0 is always at position 0.
        // There is no need to compute the entire buffer; all we need to keep track of is the value at position 1.
        int result = -1;
        int currentPosition = 0;
        for (int i = 1; i <= 50_000_000; i++) {
            currentPosition = (currentPosition + stepSize) % i + 1;
            if (currentPosition == 1) {
                result = i;
            }
        }
        
        return Integer.valueOf(result);
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        SpinlockPart2 instance = new SpinlockPart2();

        String result = instance.solve("input-day17-2017.txt");

        LOGGER.info(result);
    }
}
