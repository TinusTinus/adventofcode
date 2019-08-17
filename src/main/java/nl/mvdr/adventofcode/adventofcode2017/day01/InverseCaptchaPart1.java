package nl.mvdr.adventofcode.adventofcode2017.day01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Solution to part 1 of the day 1 puzzle of 2017's Advent of Code:
 * <a href="https://adventofcode.com/2017/day/1">Inverse Captcha</a>.
 *
 * @author Martijn van de Rijdt
 */
public class InverseCaptchaPart1 extends InverseCaptcha {

    private static final Logger LOGGER = LoggerFactory.getLogger(InverseCaptchaPart1.class);
    
    @Override
    int compareToIndex(int index, int numberOfDigits) {
        return (index + 1) % numberOfDigits;
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        InverseCaptchaPart1 instance = new InverseCaptchaPart1();

        String result = instance.solve("input-day01-2017.txt");

        LOGGER.info(result);
    }
}
