package nl.mvdr.adventofcode.adventofcode2018.day05;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.PathSolver;

/**
 * Solution to the day 5 puzzle of 2018's Advent of Code:
 * <a href="https://adventofcode.com/2018/day/5">Alchemical Reduction</a>.
 *
 * @author Martijn van de Rijdt
 */
public class AlchemicalReduction implements PathSolver<Integer> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AlchemicalReduction.class);
    
    @Override
    public Integer solve(Path inputFilePath) throws IOException {
        int result = Files.lines(inputFilePath)
                .findFirst()
                .map(Polymers::react)
                .get()
                .length();
        return Integer.valueOf(result);
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        AlchemicalReduction instance = new AlchemicalReduction();

        String result = instance.solve("input-day05-2018.txt");

        LOGGER.info(result);
    }
}
