package nl.mvdr.adventofcode.adventofcode2019.day10;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
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
    public int solve(Stream<String> lines) {
        Set<Point> asteroids = parseAsteroids(lines);
        
        AsteroidField asteroidField = asteroids.stream()
                .map(candidateStation -> new AsteroidField(candidateStation, asteroids))
                .max(Comparator.comparingInt(AsteroidField::visibleAsteroids))
                .orElseThrow();
        
        return solve(asteroidField);
    }

    /**
     * Parses the input into a set of asteroids.
     * 
     * @param lines lines from the input text file
     * @return locations of all of the asteroids from the input
     */
    private Set<Point> parseAsteroids(Stream<String> lines) {
        Set<Point> asteroids = new HashSet<>();
        Point.parse2DMap(lines.toList(), (point, c) -> {
            if (c == '#') {
                asteroids.add(point);
            }
        });
        LOGGER.debug("Asteroids: {}", asteroids);
        return asteroids;
    }
    
    /**
     * Solver method.
     * 
     * @param asteroidField the asteroid field with the optimal location for the station
     * @return puzzle answer
     */
    abstract int solve(AsteroidField asteroidField);
}
