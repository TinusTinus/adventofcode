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
        return validAdapterArrangements(0, 1, joltages);
    }
    
    /**
     * Determines the number of valid adapter arrangements (in a naive recursive way).
     * 
     * Note that this solution may be correct, but is too slow for the puzzle input.
     * 
     * @param previousJoltage joltage of the last used adapter
     * @param index index of the next possible adpater
     * @param joltages all joltages
     * @return number of valid arrangements
     */
    long validAdapterArrangements(int previousJoltage, int index, int[] joltages) {
        long result;
        if (3 < joltages[index] - previousJoltage) {
            // Not a valid adapter arrangement
            result = 0L;
        } else if (index == joltages.length - 1) {
            // Valid complete arrangement found
            result = 1L;
        } else {
            // Either skip the adapter at index, or use it
            result = validAdapterArrangements(previousJoltage, index + 1, joltages)
                    + validAdapterArrangements(joltages[index], index + 1, joltages);
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
 