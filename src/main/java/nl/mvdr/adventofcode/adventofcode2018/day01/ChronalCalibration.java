package nl.mvdr.adventofcode.adventofcode2018.day01;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.PathSolver;

/**
 * Solution to the day 1 puzzle of 2018's Advent of Code:
 * <a href="https://adventofcode.com/2018/day/1">Chronal Calibration</a>.
 *
 * @author Martijn van de Rijdt
 */
public class ChronalCalibration implements PathSolver<Integer> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChronalCalibration.class);
    
    @Override
    public Integer solve(Path inputFilePath) throws IOException {
        int sum = Files.lines(inputFilePath)
                // ignore empty lines (the last line in the file)
                .filter(Objects::nonNull)
                .filter(line -> !line.isBlank())
                // parse each line to an integer
                .mapToInt(Integer::parseInt)
                .sum();

        return Integer.valueOf(sum);
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        ChronalCalibration instance = new ChronalCalibration();

        String result = instance.solve("input-day01-2018.txt");

        LOGGER.info(result);
    }
}
