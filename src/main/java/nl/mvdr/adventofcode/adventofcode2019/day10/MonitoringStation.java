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
     * @param linesStream lines from the input text file
     * @return locations of all of the asteroids from the input
     */
    private Set<Point> parseAsteroids(Stream<String> linesStream) {
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
