package nl.mvdr.adventofcode.adventofcode2017.day03;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.PathSolver;
import nl.mvdr.adventofcode.point.Direction;
import nl.mvdr.adventofcode.point.Point;

/**
 * Solution to the day 3 puzzle of 2017's Advent of Code:
 * <a href="https://adventofcode.com/2017/day/3">Spiral Memory</a>.
 *
 * @author Martijn van de Rijdt
 */
public class SpiralMemoryPart1 implements PathSolver<Integer> {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpiralMemoryPart1.class);
    
    /**
     * {@inheritDoc}
     * 
     * @return number of steps
     */
    @Override
    public Integer solve(Path inputFilePath) throws IOException {
        String input = Files.lines(inputFilePath)
                .findFirst()
                .get();
        int square = Integer.parseInt(input);
        
        Point startingPoint = new Point(0, 0);
        
        Point point = startingPoint;
        
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
        
        return Integer.valueOf(point.manhattanDistance(startingPoint));
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
