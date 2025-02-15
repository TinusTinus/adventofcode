package nl.mvdr.adventofcode.adventofcode2017.day06;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Solution to the day 6 puzzle of 2017's Advent of Code:
 * <a href="https://adventofcode.com/2017/day/6">Memory Reallocation</a>.
 *
 * @author Martijn van de Rijdt
 */
public class MemoryReallocationPart2 extends MemoryReallocation {

    private static final Logger LOGGER = LoggerFactory.getLogger(MemoryReallocationPart2.class);

    /**
     * {@inheritDoc}
     * 
     * @return length of the cycle
     */
    @Override
    protected int solve(List<Integer> banks, Map<List<Integer>, Integer> history) {
        int cyclesBeforeFirstOccurrence = history.get(banks).intValue();
        int cyclesBeforeSecondOccurrence = history.size();
        return cyclesBeforeSecondOccurrence - cyclesBeforeFirstOccurrence;
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        MemoryReallocationPart2 instance = new MemoryReallocationPart2();

        String result = instance.solve("input-day06-2017.txt");

        LOGGER.info(result);
    }
}
