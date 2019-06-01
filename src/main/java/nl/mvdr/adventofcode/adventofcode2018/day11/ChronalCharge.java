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
abstract class ChronalCharge implements PathSolver {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ChronalCharge.class);
    
    protected static final int GRID_SIZE = 300;
    
    @Override
    public String solve(Path inputFilePath) throws IOException {
        int serialNumber = Integer.parseInt(Files.lines(inputFilePath).findFirst().get());
        LOGGER.debug("Serial number: {}", serialNumber);
        
        Square square = solve(serialNumber);
        
        return square.toString();
    }

    /**
     * Solver method.
     * 
     * @param serialNumber grid serial number
     * @return the square with the maximum power level
     */
    abstract protected Square solve(int serialNumber);
}
