package nl.mvdr.adventofcode.adventofcode2017.day03;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.solver.IntSolver;
import nl.mvdr.adventofcode.point.Direction;
import nl.mvdr.adventofcode.point.Point;

/**
 * Solution to the day 3 puzzle of 2017's Advent of Code:
 * <a href="https://adventofcode.com/2017/day/3">Spiral Memory</a>.
 *
 * @author Martijn van de Rijdt
 */
public class SpiralMemoryPart1 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpiralMemoryPart1.class);
    
    /**
     * {@inheritDoc}
     * 
     * @return number of steps
     */
    @Override
    public int solve(Stream<String> lines) {
        String input = lines.findFirst().orElseThrow();
        int square = Integer.parseInt(input);
        
        Point point = Point.ORIGIN;
        
        Set<Point> points = new HashSet<>();
        points.add(point);
        
        Direction direction = Direction.DOWN;
        
        for (int i = 1; i != square; i++) {
            Point nextPoint = direction.turnCounterClockwise().move(point);
            if (points.contains(nextPoint)) {
                nextPoint = direction.move(point);
            } else {
                direction = direction.turnCounterClockwise();
            }
            points.add(nextPoint);
            point = nextPoint;
        }
        
        return point.manhattanDistanceToOrigin();
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        SpiralMemoryPart1 instance = new SpiralMemoryPart1();

        String result = instance.solve("input-day03-2017.txt");

        LOGGER.info(result);
    }
}
