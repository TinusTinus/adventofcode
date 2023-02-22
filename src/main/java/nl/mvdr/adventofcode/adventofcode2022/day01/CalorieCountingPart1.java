package nl.mvdr.adventofcode.adventofcode2022.day01;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Solution to the day 1 puzzle of 2022's Advent of Code:
 * <a href="https://adventofcode.com/2022/day/1">Calorie Counting</a>.
 *
 * @author Martijn van de Rijdt
 */
public class CalorieCountingPart1 extends CalorieCounting {

    private static final Logger LOGGER = LoggerFactory.getLogger(CalorieCountingPart1.class);

    @Override
    protected int solve(List<Integer> elves) {
        return elves.stream()
                .mapToInt(Integer::intValue)
                .max()
                .orElseThrow();
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        CalorieCountingPart1 instance = new CalorieCountingPart1();

        String result = instance.solve("input-day01-2022.txt");

        LOGGER.info(result);
    }
}
 