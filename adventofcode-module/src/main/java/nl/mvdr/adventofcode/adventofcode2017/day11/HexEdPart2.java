package nl.mvdr.adventofcode.adventofcode2017.day11;

import java.util.List;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.solver.IntSolver;

/**
 * Solution to the day 11 puzzle of 2017's Advent of Code:
 * <a href="https://adventofcode.com/2017/day/11">Hed Ed</a>.
 *
 * @author Martijn van de Rijdt
 */
public class HexEdPart2 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(HexEdPart2.class);
    
    /**
     * {@inheritDoc}
     * 
     * @return maximum distance
     */
    @Override
    public int solve(Stream<String> lines) {
        List<HexagonalDirection> directions = HexagonalDirection.parse(lines);
        
        LOGGER.debug("Directions: {}", directions);
        
        Hexagon hexagon = Hexagon.ORIGIN;
        int maxDistance = 0;
        for (HexagonalDirection direction : directions) {
            hexagon = hexagon.move(direction);
            maxDistance = Math.max(maxDistance, hexagon.distanceToOrigin());
        }
        
        return maxDistance;
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        HexEdPart2 instance = new HexEdPart2();

        String result = instance.solve("input-day11-2017.txt");

        LOGGER.info(result);
    }
}
