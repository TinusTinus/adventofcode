package nl.mvdr.adventofcode.adventofcode2017.day17;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.PathSolver;

/**
 * Solution to the day 17 puzzle of 2017's Advent of Code:
 * <a href="https://adventofcode.com/2017/day/17">Spinlock</a>.
 *
 * @author Martijn van de Rijdt
 */
public class SpinlockPart2 implements PathSolver<Integer> {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpinlockPart2.class);
    
    @Override
    public Integer solve(Path inputFilePath) throws IOException {
        int stepSize = Files.lines(inputFilePath)
                .mapToInt(Integer::parseInt)
                .findFirst()
                .getAsInt();

        int result = -1;
        
        int currentPosition = 0;
        
        for (int i = 1; i <= 50_000_000; i++) {
            currentPosition = (currentPosition + stepSize) % i + 1;
            if (currentPosition == 1) {
                result = i;
            }
        }
        
        return Integer.valueOf(result);
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        SpinlockPart2 instance = new SpinlockPart2();

        String result = instance.solve("input-day17-2017.txt");

        LOGGER.info(result);
    }
}
