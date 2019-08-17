package nl.mvdr.adventofcode.adventofcode2017.day03;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Deque;
import java.util.LinkedList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.PathSolver;
import nl.mvdr.adventofcode.point.Point;

/**
 * Solution to the day 3 puzzle of 2018's Advent of Code:
 * <a href="https://adventofcode.com/2018/day/3">Spiral Memory</a>.
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
        
        Deque<Point> points = new LinkedList<>();
        points.add(new Point(0, 0));
        
        Direction direction = Direction.DOWN;
        
        for (int i = 1; i != square; i++) {
            Point point = direction.turnCouterClockwise().move(points.getLast());
            if (points.contains(point)) {
                point = direction.move(points.getLast());
            } else {
                direction = direction.turnCouterClockwise();
            }
            points.add(point);
        }
        
        return Integer.valueOf(points.getFirst().manhattanDistance(points.getLast()));
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
