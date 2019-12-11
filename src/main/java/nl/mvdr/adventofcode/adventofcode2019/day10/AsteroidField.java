package nl.mvdr.adventofcode.adventofcode2019.day10;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

import nl.mvdr.adventofcode.point.Point;

/**
 * Representation of an asteroid field, after a monitoring station has been
 * built on one of its asteroids.
 *
 * @author Martijn van de Rijdt
 */
class AsteroidField {
    
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
                    asteroidsByAngle.computeIfAbsent(Double.valueOf(angle), a -> new HashSet<>()).add(asteroid);
                });
    }

    /** @return the number of asteroids visible from the station */
    int visibleAsteroids() {
        return asteroidsByAngle.size();
    }
    
    /** @return location of the asteroid containing the monitoring station */
    Point getStation() {
        return station;
    }

    /** @return the asteroids other than the one containing the monitoring station, indexed by the angle to the station */
    Map<Double, Set<Point>> getAsteroidsByAngle() {
        return asteroidsByAngle;
    }
}
