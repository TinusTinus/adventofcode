package nl.mvdr.adventofcode.adventofcode2017.day17;

import java.util.LinkedList;
import java.util.stream.IntStream;

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
    
    @Override
    int solve(int stepSize) {
        LinkedList<Integer> buffer = new LinkedList<>();
        buffer.add(Integer.valueOf(0));

        // Current position is always at index 0.
        
        for (int i = 1; i <= 2017; i++) {
            IntStream.range(0, stepSize + 1)
                    .forEach(_ -> buffer.offerLast(buffer.pollFirst()));
            
            buffer.offerFirst(Integer.valueOf(i));
        }
        
        return buffer.get(1).intValue();
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
