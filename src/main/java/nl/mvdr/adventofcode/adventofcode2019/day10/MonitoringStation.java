package nl.mvdr.adventofcode.adventofcode2019.day10;

import java.util.Comparator;
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
abstract class MonitoringStation implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(MonitoringStation.class);
    
    /**
     * {@inheritDoc}
     * 
     * @return number of visible asteroids
     */
    @Override
    public int solve(Stream<String> linesStream) {
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
        
        LOGGER.debug("Asteroids: {}", asteroids);
        
        Point station = asteroids.stream()
                .max(Comparator.comparingInt(candidate -> computeVisibleAsteroids(candidate, asteroids)))
                .orElseThrow();
        
        LOGGER.debug("Optimal monitoring station location: {}", station);
        
        asteroids.remove(station);
        
        return solve(station, asteroids);
    }
    
    /**
     * Computes the number of asteroids visible from the station.
     * 
     * @param station location of the monitoring station
     * @param asteroids set of asteroids; may include {@code station}
     * @return number of visible asteroids visible from the station (not counting the station itself)
     */
    int computeVisibleAsteroids(Point station, Set<Point> asteroids) {
        int result = 0;
        
        Set<Point> remainingAsteroids = new HashSet<>(asteroids);
        remainingAsteroids.remove(station);
        
        while (!remainingAsteroids.isEmpty()) {
            Point asteroid = remainingAsteroids.stream().findAny().orElseThrow();
            
            // Remove this asteroid.
            remainingAsteroids.remove(asteroid);
            // Also remove all other asteroids on the same line, and on the same side of the station.
            remainingAsteroids.removeIf(a -> Point.sameLine(station, asteroid, a)
                    && Math.signum(station.getX() - asteroid.getX()) == Math.signum(station.getX() - a.getX())
                    && Math.signum(station.getY() - asteroid.getY()) == Math.signum(station.getY() - a.getY()));
            
            // Exactly one of the asteroids we just removed is visible (which one does not matter).
            result++;
        }
        
        return result;
    }

    /**
     * Solver method.
     * 
     * @param station location of the optimal asteroid to contain the monitoring station
     * @param otherAsteroids locations of the other asteroids
     * @return puzzle answer
     */
    abstract int solve(Point station, Set<Point> otherAsteroids);
}
