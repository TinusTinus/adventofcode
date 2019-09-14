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
public class SpinlockPart2 extends Spinlock {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpinlockPart2.class);
    
    /** Constructor. */
    public SpinlockPart2() {
        super(50_000_000);
    }
    
    @Override
    Integer solve(List<Integer> buffer) {
        int index0 = buffer.indexOf(Integer.valueOf(0));
        int index = (index0 + 1) % buffer.size();
        return buffer.get(index); // 2518590 is too low
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
