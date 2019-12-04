package nl.mvdr.adventofcode.adventofcode2019.day04;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.LongSolver;

/**
 * Solution to the day 4 puzzle of 2019's Advent of Code:
 * <a href="https://adventofcode.com/2019/day/4">Secure Container</a>.
 *
 * @author Martijn van de Rijdt
 */
public class SecureContainerPart1 implements LongSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecureContainerPart1.class);
    
    /**
     * {@inheritDoc}
     * 
     * @return how many different passwords within the range meet the criteria
     */
    @Override
    public long solve(Stream<String> lines) {
        String password = lines.findFirst().orElseThrow();
        String[] parts = password.split("-");
        int lowerBound = Integer.parseInt(parts[0]);
        int upperBound = Integer.parseInt(parts[1]);
        
        return IntStream.range(lowerBound, upperBound)
                .parallel()
                .filter(this::validatePassword)
                .count();
    }
    
    /**
     * Validates the given password.
     * 
     * @param password password to validate; must be a 6-digit integer
     * @return whether the password is valid, that is, whether the digits are
     *         ascending and there is at least one that occurs twice
     */
    private boolean validatePassword(int password) {
        int[] digits = Integer.toString(password).chars()
                .map(c -> Integer.parseInt("" + (char)c))
                .toArray();
        
        boolean ascending = true;
        boolean doubleDigit = false;
        int i = 0;
        while (ascending && i < digits.length - 1) {
            ascending = digits[i] <= digits[i + 1];
            doubleDigit = doubleDigit && digits[i] == digits[i + 1];
        }
        
        if (ascending && doubleDigit) {
            LOGGER.info("valid password found: " + password); // TODO remove log statement
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
