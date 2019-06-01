package nl.mvdr.adventofcode.adventofcode2018.day11;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.PathSolver;

/**
 * Solution to the day 11 puzzle of 2018's Advent of Code:
 * <a href="https://adventofcode.com/2018/day/11">Chronal Charge</a>.
 *
 * @author Martijn van de Rijdt
 */
public class ChronalCharge implements PathSolver {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ChronalCharge.class);
    
    @Override
    public String solve(Path inputFilePath) throws IOException {
        int serialNumber = Integer.parseInt(Files.lines(inputFilePath).findFirst().get());
        LOGGER.debug("Serial number: {}", serialNumber);
        
        
        // TODO
        return null;
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        ChronalCharge instance = new ChronalCharge();

        String solution = instance.solve("input-day11-2018.txt");
        
        LOGGER.info(solution);
    }
}
