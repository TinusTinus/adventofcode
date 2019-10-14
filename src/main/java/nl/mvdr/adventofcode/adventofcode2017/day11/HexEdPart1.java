package nl.mvdr.adventofcode.adventofcode2017.day11;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.PathSolver;

/**
 * Solution to the day 11 puzzle of 2017's Advent of Code:
 * <a href="https://adventofcode.com/2017/day/11">Hed Ed</a>.
 *
 * @author Martijn van de Rijdt
 */
public class HexEdPart1 implements PathSolver<Integer> {

    private static final Logger LOGGER = LoggerFactory.getLogger(HexEdPart1.class);
    
    /**
     * {@inheritDoc}
     * 
     * @return steps
     */
    @Override
    public Integer solve(Path inputFilePath) throws IOException {
        List<HexagonalDirection> directions = HexagonalDirection.parse(inputFilePath);
        
        LOGGER.debug("Directions: {}", directions);
        
        Hexagon hexagon = Hexagon.ORIGIN;
        for (HexagonalDirection direction : directions) {
            hexagon = hexagon.move(direction);
        }
        
        int result = hexagon.distanceToOrigin();
        
        return Integer.valueOf(result);
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
