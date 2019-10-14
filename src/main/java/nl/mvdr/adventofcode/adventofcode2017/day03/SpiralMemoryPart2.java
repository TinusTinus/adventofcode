package nl.mvdr.adventofcode.adventofcode2017.day03;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.PathSolver;
import nl.mvdr.adventofcode.point.Direction;
import nl.mvdr.adventofcode.point.Point;

/**
 * Solution to the day 3 puzzle of 2018's Advent of Code:
 * <a href="https://adventofcode.com/2018/day/3">Spiral Memory</a>.
 *
 * @author Martijn van de Rijdt
 */
public class SpiralMemoryPart2 implements PathSolver<Integer> {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpiralMemoryPart2.class);
    
    @Override
    public Integer solve(Path inputFilePath) throws IOException {
        String input = Files.lines(inputFilePath)
                .findFirst()
                .get();
        int targetValue = Integer.parseInt(input);
        
        Point startingPoint = new Point(0, 0);
        
        Point point = startingPoint;
        
        Map<Point, Integer> memory = new HashMap<>();
        memory.put(point, Integer.valueOf(1));
        
        Direction direction = Direction.DOWN;
        
        while(memory.get(point).intValue() <= targetValue) {
            // Determine the next location for a memory bank
            Point nextPoint = direction.turnCounterClockwise().move(point);
            if (memory.containsKey(nextPoint)) {
                nextPoint = direction.move(point);
            } else {
                direction = direction.turnCounterClockwise();
            }
            
            // Compute the next value
            int value = nextPoint.neighboursIncludingDiagonals().stream()
                    .map(neighbour -> memory.getOrDefault(neighbour, Integer.valueOf(0)))
                    .mapToInt(Integer::intValue)
                    .sum();
            
            // Store it
            memory.put(nextPoint, Integer.valueOf(value));
            point = nextPoint;
        }
        
        LOGGER.debug("Memory: {}", memory);
        
        return memory.get(point);
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        SpiralMemoryPart2 instance = new SpiralMemoryPart2();

        String result = instance.solve("input-day03-2017.txt");

        LOGGER.info(result);
    }
}
