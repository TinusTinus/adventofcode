package nl.mvdr.adventofcode.adventofcode2018.day06;

import java.io.IOException;
import java.nio.file.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.PathSolver;

/**
 * Solution to the day 6 puzzle of 2018's Advent of Code:
 * <a href="https://adventofcode.com/2018/day/6">Chronal Coordinates</a>.
 *
 * @author Martijn van de Rijdt
 */
public class ChronalCoordinates implements PathSolver {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ChronalCoordinates.class);
    
    @Override
    public String solve(Path inputFilePath) throws IOException {
        // TODO Auto-generated method stub
        return null;
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        ChronalCoordinates instance = new ChronalCoordinates();

        String result = instance.solve("input-day06-2018.txt");

        LOGGER.info(result);
    }
}
