package nl.mvdr.adventofcode.adventofcode2018.day11;

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
public class ChronalChargePart1 extends ChronalCharge {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ChronalChargePart1.class);
    
    @Override
    protected Set<Square> getSquares(int[][] grid) {
        Set<Square> squares = new HashSet<>();
        for (int x = 0; x < GRID_SIZE - 3; x++) {
            for (int y = 0; y < GRID_SIZE - 3; y++) {
                squares.add(new Square(grid, x, y, 3, false));
            }
        }
        
        return squares;
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        ChronalChargePart1 instance = new ChronalChargePart1();

        String solution = instance.solve("input-day11-2018.txt");
        
        LOGGER.info(solution);
    }
}
