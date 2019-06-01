package nl.mvdr.adventofcode.adventofcode2018.day11;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Solution to the day 11 puzzle of 2018's Advent of Code:
 * <a href="https://adventofcode.com/2018/day/11">Chronal Charge</a>.
 *
 * @author Martijn van de Rijdt
 */
public class ChronalChargePart2 extends ChronalCharge {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ChronalChargePart2.class);
    
    private static final int GRID_SIZE = 300;
    
    @Override
    protected Square solve(int[][] grid) {
        Set<Square> squares = new HashSet<>();
        for (int x = 0; x < GRID_SIZE; x++) {
            for (int y = 0; y < GRID_SIZE; y++) {
                for (int squareSize = 1; squareSize < GRID_SIZE - Math.max(x, y); squareSize++) {
                    squares.add(new Square(grid, x, y, squareSize, true));
                }
            }
        }
        
        LOGGER.debug("Set of {} squares constructed.", squares.size());
        
        return squares.parallelStream()
                .max(Comparator.comparing(Square::totalPowerLevel))
                .get();
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        ChronalChargePart2 instance = new ChronalChargePart2();

        String solution = instance.solve("input-day11-2018.txt");
        
        LOGGER.info(solution);
    }
}
