package nl.mvdr.adventofcode.adventofcode2016.day01;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.IntSolver;
import nl.mvdr.adventofcode.point.Direction;
import nl.mvdr.adventofcode.point.Point;
import nl.mvdr.adventofcode.point.TurnDirection;

/**
 * Solution to the day 1 puzzle of 2016's Advent of Code:
 * <a href="https://adventofcode.com/2016/day/1">No Time for a Taxicab</a>.
 *
 * @author Martijn van de Rijdt
 */
public class TaxicabPart1 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(TaxicabPart1.class);
    
    /**
     * {@inheritDoc}
     * 
     * @return Manhattan distance to Easter Bunny Headquarters
     */
    @Override
    public int solve(Stream<String> lines) {
        String input = lines.findFirst().orElseThrow();
        
        Point startingLocation = new Point(0, 0);
        
        Direction direction = Direction.UP;
        Point location = startingLocation;
        
        for (String part : input.split(", ")) {
            TurnDirection turnDirection = TurnDirection.parse(part.charAt(0));
            direction = turnDirection.turn(direction);
            
            int numberOfSteps = Integer.parseInt(part.substring(1));
            for (int i = 0; i != numberOfSteps; i++) {
                location = direction.move(location);
            }
        }
        
        return location.manhattanDistance(startingLocation);
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
