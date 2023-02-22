package nl.mvdr.adventofcode.adventofcode2022.day01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Solution to the day 1 puzzle of 2022's Advent of Code:
 * <a href="https://adventofcode.com/2022/day/1">Calorie Counting</a>.
 *
 * @author Martijn van de Rijdt
 */
public class CalorieCountingPart2 extends CalorieCounting {

    private static final Logger LOGGER = LoggerFactory.getLogger(CalorieCountingPart2.class);

    /**
     * Constructor.
     */
    public CalorieCountingPart2() {
        super(3);
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        CalorieCountingPart2 instance = new CalorieCountingPart2();

        String result = instance.solve("input-day01-2022.txt");

        LOGGER.info(result);
    }
}
 