package nl.mvdr.adventofcode.adventofcode2016.day01;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.PathSolver;
import nl.mvdr.adventofcode.point.Direction;
import nl.mvdr.adventofcode.point.Point;

/**
 * Solution to the day 1 puzzle of 2016's Advent of Code:
 * <a href="https://adventofcode.com/2016/day/1">No Time for a Taxicab</a>.
 *
 * @author Martijn van de Rijdt
 */
public class TaxicabPart1 implements PathSolver<Integer> {

    private static final Logger LOGGER = LoggerFactory.getLogger(TaxicabPart1.class);
    
    /**
     * {@inheritDoc}
     * 
     * @return Manhattan distance to Easter Bunny Headquarters
     */
    @Override
    public Integer solve(Path inputFilePath) throws IOException {
        String input = Files.lines(inputFilePath).findFirst().get();
        
        Point startingLocation = new Point(0, 0);
        
        Direction direction = Direction.UP;
        Point location = startingLocation;
        
        for (String part : input.split(", ")) {
            if (part.charAt(0) == 'L') {
                direction = direction.turnCounterClockwise();
            } else if (part.charAt(0) == 'R') {
                direction = direction.turnClockwise();
            } else {
                throw new IllegalStateException("Unexpected input: " + part);
            }
            
            int numberOfSteps = Integer.parseInt(part.substring(1));
            for (int i = 0; i != numberOfSteps; i++) {
                location = direction.move(location);
            }
        }
        
        return Integer.valueOf(location.manhattanDistance(startingLocation));
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        TaxicabPart1 instance = new TaxicabPart1();

        String result = instance.solve("input-day01-2016.txt");

        LOGGER.info(result);
    }
}
