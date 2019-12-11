package nl.mvdr.adventofcode.adventofcode2019.day10;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
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
        
        return asteroids.stream()
                .collect(Collectors.toMap(Function.identity(), candidateStation -> mapAsteroidsByAngle(candidateStation, asteroids)))
                .entrySet()
                .stream()
                .max(Comparator.comparingInt(entry -> entry.getValue().size()))
                .stream()
                .mapToInt(entry -> solve(entry.getKey(), entry.getValue()))
                .findFirst()
                .getAsInt();
    }
    
    /**
     * Creates a map containing the given asteroids, indexed by their angle with respect to the station.
     * 
     * @param station location of the monitoring station
     * @param asteroids set of asteroids; may include {@code station}
     * @return map of all asteroids (excluding {@code station}), indexed by their angle with respect to {@code station}
     */
    private Map<Double, Set<Point>> mapAsteroidsByAngle(Point station, Set<Point> asteroids) {
        Map<Double, Set<Point>> result = new HashMap<>();
        asteroids.stream()
                .filter(Predicate.not(station::equals))
                .forEach(asteroid -> {
                    double angle = station.computeAngle(asteroid);
                    result.computeIfAbsent(Double.valueOf(angle), a -> new HashSet<>()).add(asteroid);
                });
        return result;
    }

    /**
     * Solver method.
     * 
     * @param station location of the optimal asteroid to contain the monitoring station
     * @param otherAsteroidsByAngle locations of the other asteroids, indexed by their angle compared to the station
     * @return puzzle answer
     */
    abstract int solve(Point station, Map<Double, Set<Point>> otherAsteroidsByAngle);
}
