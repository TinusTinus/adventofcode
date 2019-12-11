package nl.mvdr.adventofcode.adventofcode2019.day10;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.point.Point;

/**
 * Solution to the day 10 puzzle of 2019's Advent of Code:
 * <a href="https://adventofcode.com/2019/day/10">Monitoring Station</a>.
 *
 * @author Martijn van de Rijdt
 */
public class MonitoringStationPart2 extends MonitoringStation {

    private static final Logger LOGGER = LoggerFactory.getLogger(MonitoringStationPart2.class);
    
    /**
     * {@inheritDoc}
     * 
     * @return identification of the 200th asteroid to be completely vaporised by a giant laser
     */
    @Override
    int solve(AsteroidField asteroidField) {
        // Construct a list of angles, in the order we will inspect them.
        // Start with 90 degrees (up), and place them in decreasing order (clockwise).
        List<Double> angles = asteroidField.getAsteroidsByAngle().keySet().stream()
                .sorted(Comparator.<Double>comparingDouble(a -> a.doubleValue() <= 90D ? a.doubleValue() + 360 : a.doubleValue()).reversed())
                .collect(Collectors.toList());
        LOGGER.debug("Angles: {}", angles);
        
        Point result = null;
        int i = 0;
        for (int asteroidsDestroyed = 0; asteroidsDestroyed != 200; asteroidsDestroyed++) {
            // Search for the next angle still containing asteroids
            while (asteroidField.getAsteroidsByAngle().get(angles.get(i)).isEmpty()) {
                i = (i + 1) % angles.size();
            }
            
            Set<Point> nextAsteroids = asteroidField.getAsteroidsByAngle().get(angles.get(i));
            
            LOGGER.debug("Vaporising one of the asteroids at angle {}: {}", angles.get(i), nextAsteroids);
            
            // Find the closest asteroid
            result = nextAsteroids.stream()
                    .min(Comparator.comparingInt(asteroidField.getStation()::manhattanDistance))
                    .orElseThrow();
            
            LOGGER.debug("Vaporising {}", result);
            nextAsteroids.remove(result);
            
            i = (i + 1) % angles.size();
        }
        
        return result.getX() * 100 + result.getY();
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        MonitoringStationPart2 instance = new MonitoringStationPart2();

        String result = instance.solve("input-day10-2019.txt");

        LOGGER.info(result);
    }
}
