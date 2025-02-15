package nl.mvdr.adventofcode.adventofcode2017.day19;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Solution to the day 19 puzzle of 2017's Advent of Code:
 * <a href="https://adventofcode.com/2017/day/19">A Series of Tubes</a>.
 *
 * @author Martijn van de Rijdt
 */
public class SeriesOfTubesPart2 extends SeriesOfTubes<Integer> {

    private static final Logger LOGGER = LoggerFactory.getLogger(SeriesOfTubesPart2.class);
    
    @Override
    Integer solve(String letters, int steps) {
        return Integer.valueOf(steps);
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        SeriesOfTubesPart2 instance = new SeriesOfTubesPart2();

        String result = instance.solve("input-day19-2017.txt");

        LOGGER.info(result);
    }
}
