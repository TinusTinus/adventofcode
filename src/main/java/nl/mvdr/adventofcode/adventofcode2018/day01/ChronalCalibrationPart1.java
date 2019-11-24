package nl.mvdr.adventofcode.adventofcode2018.day01;

import java.util.function.Predicate;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.IntSolver;

/**
 * Solution to the day 1 puzzle of 2018's Advent of Code:
 * <a href="https://adventofcode.com/2018/day/1">Chronal Calibration</a>.
 *
 * @author Martijn van de Rijdt
 */
public class ChronalCalibrationPart1 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChronalCalibrationPart1.class);
    
    @Override
    public int solve(Stream<String> lines) {
        return lines
                // ignore empty lines (the last line in the file)
                .filter(Predicate.not(String::isBlank))
                // parse each line to an integer
                .mapToInt(Integer::parseInt)
                .sum();
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        ChronalCalibrationPart1 instance = new ChronalCalibrationPart1();

        String result = instance.solve("input-day01-2018.txt");

        LOGGER.info(result);
    }
}
