package nl.mvdr.adventofcode.adventofcode2019.day04;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Solution to the day 4 puzzle of 2019's Advent of Code:
 * <a href="https://adventofcode.com/2019/day/4">Secure Container</a>.
 *
 * @author Martijn van de Rijdt
 */
public class SecureContainerPart2 extends SecureContainer {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecureContainerPart2.class);
    
    @Override
    boolean validatePassword(int password) {
        boolean ascending = true;
        Map<Integer, Integer> digitCounts = new HashMap<>();
        
        int previousDigit = password % 10;
        digitCounts.put(Integer.valueOf(previousDigit), Integer.valueOf(1));
        int remainingDigits = password / 10;
        
        // Consider digits right-to-left
        while (ascending && 0 < remainingDigits) {
            int digit = remainingDigits % 10;
            remainingDigits = remainingDigits / 10;
            
            ascending = digit <= previousDigit;
            digitCounts.compute(Integer.valueOf(digit), 
                    (key, count) -> count == null ? Integer.valueOf(1) : Integer.valueOf(count.intValue() + 1));
            
            previousDigit = digit;
        }
        
        return ascending && digitCounts.values().contains(Integer.valueOf(2));
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        SecureContainerPart2 instance = new SecureContainerPart2();

        String result = instance.solve("input-day04-2019.txt");

        LOGGER.info(result);
    }
}
