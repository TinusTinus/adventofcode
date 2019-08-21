package nl.mvdr.adventofcode.adventofcode2017.day05;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Solution to the day 5 puzzle of 2017's Advent of Code:
 * <a href="https://adventofcode.com/2018/day/5">A Maze of Twisty Trampolines, All Alike</a>.
 *
 * @author Martijn van de Rijdt
 */
public class TrampolinesPart2 extends Trampolines {

    private static final Logger LOGGER = LoggerFactory.getLogger(TrampolinesPart2.class);

    @Override
    boolean increase(int offset) {
        return offset < 3;
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        TrampolinesPart2 instance = new TrampolinesPart2();

        String result = instance.solve("input-day05-2017.txt");

        LOGGER.info(result);
    }
}
