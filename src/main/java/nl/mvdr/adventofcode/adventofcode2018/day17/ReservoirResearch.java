package nl.mvdr.adventofcode.adventofcode2018.day17;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.PathSolver;
import nl.mvdr.adventofcode.adventofcode2018.point.Point;

/**
 * Solution to the day 17 puzzle of 2018's Advent of Code:
 * <a href="https://adventofcode.com/2018/day/17">Reservoir Research</a>.
 *
 * @author Martijn van de Rijdt
 */
public class ReservoirResearch implements PathSolver {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ReservoirResearch.class);
    
    @Override
    public String solve(Path inputFilePath) throws IOException {
        Set<Point> claySquareMeters = Point.parseRanges(inputFilePath);
        LOGGER.debug("Clay: {}", claySquareMeters);
        
        Point spring = new Point(500, 0);
        
        // TODO move this to a toString method instead of sysouting
        int minX = claySquareMeters.stream()
                .mapToInt(Point::getX)
                .min()
                .getAsInt();
        minX = Math.min(minX, spring.getX());
        
        int maxX = claySquareMeters.stream()
                .mapToInt(Point::getX)
                .max()
                .getAsInt();
        maxX = Math.max(maxX, spring.getX());
        
        int minY = claySquareMeters.stream()
                .mapToInt(Point::getY)
                .min()
                .getAsInt();
        minY = Math.min(minY, spring.getY());
        
        int maxY = claySquareMeters.stream()
                .mapToInt(Point::getY)
                .max()
                .getAsInt();
        maxY = Math.max(maxY, spring.getY());
        
        for (int y = minY - 1; y != maxY + 2; y++) {
            for (int x = minX - 1; x != maxX + 2; x++) {
                Point point = new Point(x, y);
                
                char c;
                if (spring.equals(point)) {
                    c = '+';
                } else if (claySquareMeters.contains(point)) {
                    c = '#';
                } else {
                    c = '.';
                }
                System.out.print(c);
            }
            System.out.println();
        }
        
        
        
        return null; // TODO
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        ReservoirResearch solver = new ReservoirResearch();
        String solution = solver.solve("input-day17-2018.txt");
        LOGGER.info(solution);
    }
}
