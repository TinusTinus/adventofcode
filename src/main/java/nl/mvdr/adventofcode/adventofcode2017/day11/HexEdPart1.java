package nl.mvdr.adventofcode.adventofcode2017.day11;

import java.util.List;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.IntSolver;

/**
 * Solution to the day 11 puzzle of 2017's Advent of Code:
 * <a href="https://adventofcode.com/2017/day/11">Hed Ed</a>.
 *
 * @author Martijn van de Rijdt
 */
public class HexEdPart1 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(HexEdPart1.class);
    
    /**
     * {@inheritDoc}
     * 
     * @return steps
     */
    @Override
    public int solve(Stream<String> lines) {
        List<HexagonalDirection> directions = HexagonalDirection.parse(lines);
        
        LOGGER.debug("Directions: {}", directions);
        
        Hexagon hexagon = Hexagon.ORIGIN;
        for (HexagonalDirection direction : directions) {
            hexagon = hexagon.move(direction);
        }
        
        return hexagon.distanceToOrigin();
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        HexEdPart1 instance = new HexEdPart1();

        String result = instance.solve("input-day11-2017.txt");

        LOGGER.info(result);
    }
}
