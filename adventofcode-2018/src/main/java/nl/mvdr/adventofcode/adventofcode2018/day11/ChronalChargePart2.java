package nl.mvdr.adventofcode.adventofcode2018.day11;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
    
    @Override
    protected Set<Square> getSquares(int[][] grid) {
        return IntStream.range(0, GRID_SIZE)
                .mapToObj(Integer::valueOf)
                .parallel()
                .flatMap(x -> IntStream.range(0, GRID_SIZE)
                        .mapToObj(Integer::valueOf)
                        .parallel()
                        .flatMap(y -> 
                                IntStream.range(1, GRID_SIZE - Math.max(x.intValue(), y.intValue()))
                                .mapToObj(squareSize -> new Square(grid, x.intValue(), y.intValue(), squareSize, true))))
                .collect(Collectors.toSet());
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
