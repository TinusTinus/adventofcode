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
        long n1 = 1L;
        long n0 = 1L;
        int index = joltages.length - 3;
        long result = 1L;
        
        while (0 <= index) {
            
            long previousResult = result;
            
            if (joltages[index + 2] - joltages[index] <= 3) {
                // We can skip the adapter at index + 1
                result = result + n0;
            }
            if (index + 3 < joltages.length && joltages[index + 3] - joltages[index] <= 3) {
                // We can skip the adapters at index + 1 and index + 2
                result = result + n1;
            }
            
            n1 = n0;
            n0 = previousResult;
            
            index--;
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
 