package nl.mvdr.adventofcode.adventofcode2019.day10;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.point.Point;

/**
 * Solution to the day 10 puzzle of 2019's Advent of Code:
 * <a href="https://adventofcode.com/2019/day/10">Monitoring Station</a>.
 *
 * @author Martijn van de Rijdt
 */
public class MonitoringStationPart1 extends MonitoringStation {

    private static final Logger LOGGER = LoggerFactory.getLogger(MonitoringStationPart1.class);
    
    /**
     * {@inheritDoc}
     * 
     * @return number of visible asteroids
     */
    @Override
    int solve(Set<Point> asteroids) {
        return asteroids.stream()
                .mapToInt(asteroid -> computeVisibleAsteroids(asteroid, asteroids))
                .max()
                .getAsInt();
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
            
            // Remove this asteroid.
            remainingAsteroids.remove(asteroid);
            // Also remove all other asteroids on the same line, and on the same side.
            remainingAsteroids.removeIf(a -> Point.sameLine(point, asteroid, a)
                    && Math.signum(point.getX() - asteroid.getX()) == Math.signum(point.getX() - a.getX())
                    && Math.signum(point.getY() - asteroid.getY()) == Math.signum(point.getY() - a.getY()));
            
            // Exactly one of the asteroids we just removed is visible (which one does not matter).
            result++;
        }
        
        return result;
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
