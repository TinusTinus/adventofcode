package nl.mvdr.adventofcode.adventofcode2018.day11;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.PathSolver;

/**
 * Solution to the day 11 puzzle of 2018's Advent of Code:
 * <a href="https://adventofcode.com/2018/day/11">Chronal Charge</a>.
 *
 * @author Martijn van de Rijdt
 */
abstract class ChronalCharge implements PathSolver<Square> {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ChronalCharge.class);
    
    protected static final int GRID_SIZE = 300;
    
    @Override
    public Square solve(Path inputFilePath) throws IOException {
        int serialNumber = Integer.parseInt(Files.lines(inputFilePath).findFirst().get());
        LOGGER.debug("Serial number: {}", serialNumber);
        
        int[][] grid = new int[GRID_SIZE][GRID_SIZE];
        
        for (int x = 0; x < GRID_SIZE; x++) {
            for (int y = 0; y < GRID_SIZE; y++) {
                grid[x][y] = calculatePowerLevel(x, y, serialNumber);
            }
        }
        
        LOGGER.debug("Grid constructed.");
        
        Set<Square> squares = getSquares(grid);
        
        LOGGER.debug("Set of {} squares constructed.", squares.size());
        
        return squares.parallelStream()
                .max(Comparator.comparing(Square::getTotalPowerLevel))
                .get();
    }
    
    /**
     * Computes the power level of a cell.
     * 
     * The power level in a given fuel cell can be found through the following process:
     * <ul>
     * <li>Find the fuel cell's rack ID, which is its X coordinate plus 10.</li>
     * <li>Begin with a power level of the rack ID times the Y coordinate.</li>
     * <li>Increase the power level by the value of the grid serial number (your puzzle input).</li>
     * <li>Set the power level to itself multiplied by the rack ID.</li>
     * <li>Keep only the hundreds digit of the power level (so 12345 becomes 3; numbers with no hundreds digit become 0).</li>
     * <li>Subtract 5 from the power level.</li>
     * </ul>
     * 
     * @return this cell's power level
     */
    // Default visibility for unit tests.
    static int calculatePowerLevel(int x, int y, int serialNumber) {
        // Find the fuel cell's rack ID, which is its X coordinate plus 10.
        int rackId = x + 10;
        
        // Begin with a power level of the rack ID times the Y coordinate.
        int result = rackId * y;
        // Increase the power level by the value of the grid serial number (your puzzle input).
        result = result + serialNumber;
        // Set the power level to itself multiplied by the rack ID.
        result = result * rackId;
        // Keep only the hundreds digit of the power level (so 12345 becomes 3; numbers with no hundreds digit become 0).
        result = result / 100;
        result = result % 10;
        // Subtract 5 from the power level.
        result = result - 5;
        
        return result;
    }

    /**
     * Solver method.
     * 
     * @param grid grid
     * @return all squares to be considered
     */
    abstract protected Set<Square> getSquares(int[][] grid);
}
