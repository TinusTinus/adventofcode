package nl.mvdr.adventofcode.adventofcode2020.day10;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Solution to the day 10 puzzle of 2020's Advent of Code:
 * <a href="https://adventofcode.com/2020/day/10">Adapter Array</a>.
 *
 * @author Martijn van de Rijdt
 */
public class AdapterArrayPart2 extends AdapterArray {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdapterArrayPart2.class);

    /**
     * {@inheritDoc}
     * 
     * @return the number of valid adapter arrangements
     */
    @Override
    long solve(int[] joltages) {
        return validAdapterArrangements(0, joltages);
    }
    
    /**
     * Determines the number of valid adapter arrangements (in a naive recursive way).
     * 
     * Note that this solution may be correct, but is too slow for the puzzle input.
     * 
     * @param index index of the next adpater to use
     * @param joltages all joltages
     * @return number of valid arrangements
     */
    private long validAdapterArrangements(int index, int[] joltages) {
        long result;
        if (index == joltages.length - 1) {
            // Valid complete arrangement found
            result = 1L;
        } else {
            // Use the next adapter...
            result = validAdapterArrangements(index + 1, joltages);
            // ... skip (only) the next adapter...
            if (index + 2 < joltages.length && joltages[index + 2] - joltages[index] <= 3) {
                result = result + validAdapterArrangements(index + 2, joltages);
            }
            // ... or skip the next two adapters.
            if (index + 3 < joltages.length && joltages[index + 3] - joltages[index] <= 3) {
                result = result + validAdapterArrangements(index + 3, joltages);
            }
            // Skipping more adapters is definitely not possible, as the joltage gap is guaranteed to be greater than 3.
        }
        return result;
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        AdapterArrayPart2 instance = new AdapterArrayPart2();

        String result = instance.solve("input-day10-2020.txt");

        LOGGER.info(result);
    }
}
 