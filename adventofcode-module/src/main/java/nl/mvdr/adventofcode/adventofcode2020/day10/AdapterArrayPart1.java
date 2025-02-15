package nl.mvdr.adventofcode.adventofcode2020.day10;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Solution to the day 10 puzzle of 2020's Advent of Code:
 * <a href="https://adventofcode.com/2020/day/10">Adapter Array</a>.
 *
 * @author Martijn van de Rijdt
 */
public class AdapterArrayPart1 extends AdapterArray {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdapterArrayPart1.class);

    /**
     * {@inheritDoc}
     * 
     * @return the number of 1-jolt differences multiplied by the number of 3-jolt differences
     */
    @Override
    long solve(int[] joltages) {
        int oneJoltDifferences = 0;
        int threeJoltDifferences = 0;
        
        for (int i = 0; i != joltages.length - 1; i++) {
            int difference = joltages[i + 1] - joltages[i];
            if (difference == 1) {
                oneJoltDifferences++;
            } else if (difference == 3) {
                threeJoltDifferences++;
            }
        }
        
        return oneJoltDifferences * threeJoltDifferences;
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        AdapterArrayPart1 instance = new AdapterArrayPart1();

        String result = instance.solve("input-day10-2020.txt");

        LOGGER.info(result);
    }
}
 