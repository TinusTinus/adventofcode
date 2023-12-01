package nl.mvdr.adventofcode.adventofcode2023.day01;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.IntSolver;

/**
 * Solution to the day 1 puzzle of 2023's Advent of Code:
 * <a href="https://adventofcode.com/2023/day/1">Trebuchet?!</a>.
 *
 * @author Martijn van de Rijdt
 */
public class TrebuchetPart1 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(TrebuchetPart1.class);

    @Override
    public int solve(Stream<String> lines) {
        return lines.mapToInt(TrebuchetPart1::findCalibrationValue)
            .sum();
    }
    
    /**
     * Finds a calibration value.
     * 
     * @param line line from the input, for example: "pqr3stu8vwx"
     * @return the calibration value; in this example: 38
     */
    private static int findCalibrationValue(String line) {
        var digits = line.chars()
                .filter(c -> Character.isDigit((char) c))
                .mapToObj(c -> "" + (char) c)
                .toList();
        var calibrationValueString = digits.getFirst() + digits.getLast();
        return Integer.parseInt(calibrationValueString);
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        TrebuchetPart1 instance = new TrebuchetPart1();

        String result = instance.solve("input-day01-2023.txt");

        LOGGER.info(result);
    }
}
 