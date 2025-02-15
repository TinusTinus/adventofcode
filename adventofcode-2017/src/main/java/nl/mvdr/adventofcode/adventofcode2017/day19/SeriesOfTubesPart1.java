package nl.mvdr.adventofcode.adventofcode2017.day19;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Solution to the day 19 puzzle of 2017's Advent of Code:
 * <a href="https://adventofcode.com/2017/day/19">A Series of Tubes</a>.
 *
 * @author Martijn van de Rijdt
 */
public class SeriesOfTubesPart1 extends SeriesOfTubes<String> {

    private static final Logger LOGGER = LoggerFactory.getLogger(SeriesOfTubesPart1.class);
    
    @Override
    String solve(String letters, int steps) {
        return letters;
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        SeriesOfTubesPart1 instance = new SeriesOfTubesPart1();

        String result = instance.solve("input-day19-2017.txt");

        LOGGER.info(result);
    }
}
