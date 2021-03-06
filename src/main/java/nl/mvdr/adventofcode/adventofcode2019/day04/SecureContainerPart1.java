package nl.mvdr.adventofcode.adventofcode2019.day04;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Solution to the day 4 puzzle of 2019's Advent of Code:
 * <a href="https://adventofcode.com/2019/day/4">Secure Container</a>.
 *
 * @author Martijn van de Rijdt
 */
public class SecureContainerPart1 extends SecureContainer {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecureContainerPart1.class);
    
    @Override
    boolean validatePassword(int password) {
        boolean ascending = true;
        boolean doubleDigit = false;
        
        int previousDigit = password % 10;
        int remainingDigits = password / 10;
        
        // Consider digits right-to-left
        while (ascending && 0 < remainingDigits) {
            int digit = remainingDigits % 10;
            remainingDigits = remainingDigits / 10;
            
            ascending = digit <= previousDigit;
            doubleDigit = doubleDigit || previousDigit == digit;
            
            previousDigit = digit;
        }
        
        return ascending && doubleDigit;
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        SecureContainerPart1 instance = new SecureContainerPart1();

        String result = instance.solve("input-day04-2019.txt");

        LOGGER.info(result);
    }
}
