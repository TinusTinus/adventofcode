package nl.mvdr.adventofcode.adventofcode2019.day10;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.IntSolver;
import nl.mvdr.adventofcode.point.Point;

/**
 * Solution to the day 10 puzzle of 2019's Advent of Code:
 * <a href="https://adventofcode.com/2019/day/10">Monitoring Station</a>.
 *
 * @author Martijn van de Rijdt
 */
public class MonitoringStationPart1 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(MonitoringStationPart1.class);
    
    /**
     * {@inheritDoc}
     * 
     * @return number of visible asteroids
     */
    @Override
    public int solve(Stream<String> lines) {
        Set<Point> asteroids = parse(lines);
        
        return asteroids.stream()
                .mapToInt(asteroid -> computeVisibleAsteroids(asteroid, asteroids))
                .max()
                .getAsInt();
    }

    /**
     * Parses the contents of the input into a set of asteroid locations.
     * 
     * @param linesStream puzzle input
     * @return locations of the asteroids in the puzzle input
     */
    private Set<Point> parse(Stream<String> linesStream) {
        Set<Point> asteroids = new HashSet<>();
        List<String> lines = linesStream.collect(Collectors.toList());
        for (int y = 0; y != lines.size(); y++) {
            String line = lines.get(y);
            for (int x = 0; x != line.length(); x++) {
                char c = line.charAt(x);
                if (c == '#') {
                    asteroids.add(new Point(x, y));
                }
            }
        }
        return Set.copyOf(asteroids);
    }
    
    /**
     * Computes the number of asteroids visible from the given point.
     * 
     * Note that the set of asteroids includes the point itself, but that point should not be counted.
     * 
     * @param point point
     * @param asteroids all asteroids
     * @return number of visible asteroids
     */
    private int computeVisibleAsteroids(Point point, Set<Point> asteroids) {
        int result = 0;
        
        Set<Point> remainingAsteroids = new HashSet<>(asteroids);
        remainingAsteroids.remove(point);
        
        while (!remainingAsteroids.isEmpty()) {
            Point asteroid = remainingAsteroids.stream().findAny().orElseThrow();
            
            // Remove this asteroid and all other asteroids that are on the same line.
            remainingAsteroids.remove(asteroid);
            remainingAsteroids.removeIf(a -> sameLine(point, asteroid, a));
            
            // Exactly one of the asteroids we just removed is visible (which one does not matter).
            result++;
        }
        
        return result;
    }
    
    /**
     * Checks whether the given three points are all on the same line.
     * 
     * @param point0 first point
     * @param point1 second point
     * @param point2 third point
     * @return whether the three points are all on the same line
     */
    private boolean sameLine(Point point0, Point point1, Point point2) {
        int crossProduct = (point1.getX() - point0.getX()) * (point2.getY() - point0.getY())
                - (point1.getY() - point0.getY()) * (point2.getX() - point0.getX());
                
         return crossProduct == 0;
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        MonitoringStationPart1 instance = new MonitoringStationPart1();

        String result = instance.solve("input-day10-2019.txt");

        LOGGER.info(result);
    }
}
