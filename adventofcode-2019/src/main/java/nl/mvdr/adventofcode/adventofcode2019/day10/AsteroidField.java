package nl.mvdr.adventofcode.adventofcode2019.day10;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.point.Point;

/**
 * Representation of an asteroid field, after a monitoring station has been
 * built on one of its asteroids.
 *
 * @author Martijn van de Rijdt
 */
class AsteroidField {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(AsteroidField.class);
    
    /** Location of the asteroid containing the monitoring station. */
    private final Point station;
    /** The other asteroids, indexed by the angle to the station. */
    private final Map<Double, Set<Point>> asteroidsByAngle;
    
    /**
     * Constructor.
     * 
     * @param station location of the monitoring station
     * @param asteroids set of asteroids; may include {@code station}
     */
    AsteroidField(Point station, Set<Point> asteroids) {
        this.station = station;

        asteroidsByAngle = new HashMap<>();
        asteroids.stream()
                .filter(Predicate.not(station::equals))
                .forEach(asteroid -> {
                    double angle = station.computeAngle(asteroid);
                    asteroidsByAngle.computeIfAbsent(Double.valueOf(angle), _ -> new HashSet<>()).add(asteroid);
                });
    }

    /** @return the number of asteroids visible from the station */
    int visibleAsteroids() {
        return asteroidsByAngle.size();
    }

    /**
     * Vaporises asteroids using the ship's rotating laser.
     * 
     * @param target number of asteroids to be destroyed
     * @return location of the last vaporised asteroid
     */
    Point vaporiseAsteroids(int target) {
        // Construct a list of angles, in the order we will inspect them.
        // Start with 90 degrees (up), and place them in decreasing order (clockwise).
        List<Double> angles = asteroidsByAngle.keySet().stream()
                .sorted(Comparator.<Double>comparingDouble(a -> a.doubleValue() <= 90D ? a.doubleValue() + 360 : a.doubleValue()).reversed())
                .collect(Collectors.toList());
        LOGGER.debug("Angles: {}", angles);
        
        Point result = null;
        int i = 0;
        for (int asteroidsDestroyed = 0; asteroidsDestroyed != target; asteroidsDestroyed++) {
            // Search for the next angle still containing asteroids
            while (asteroidsByAngle.get(angles.get(i)).isEmpty()) {
                i = (i + 1) % angles.size();
            }
            
            Set<Point> nextAsteroids = asteroidsByAngle.get(angles.get(i));
            
            LOGGER.debug("Vaporising one of the asteroids at angle {}: {}", angles.get(i), nextAsteroids);
            
            // Find the closest asteroid
            result = nextAsteroids.stream()
                    .min(Comparator.comparingInt(station::manhattanDistance))
                    .orElseThrow();
            
            LOGGER.debug("Vaporising {}", result);
            nextAsteroids.remove(result);
            
            i = (i + 1) % angles.size();
        }
        
        return result;
    }
}
