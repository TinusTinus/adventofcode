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
public class HexEdPart2 implements PathSolver<Integer> {

    private static final Logger LOGGER = LoggerFactory.getLogger(HexEdPart2.class);
    
    /**
     * {@inheritDoc}
     * 
     * @return maximum distance
     */
    @Override
    public Integer solve(Path inputFilePath) throws IOException {
        List<Direction> directions = Direction.parse(inputFilePath);
        
        LOGGER.debug("Directions: {}", directions);
        
        Hexagon hexagon = Hexagon.ORIGIN;
        int maxDistance = 0;
        for (Direction direction : directions) {
            hexagon = hexagon.move(direction);
            maxDistance = Math.max(maxDistance, hexagon.distanceToOrigin());
        }
        
        return Integer.valueOf(maxDistance);
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
