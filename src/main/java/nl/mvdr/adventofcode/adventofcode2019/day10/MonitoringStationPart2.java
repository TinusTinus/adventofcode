package nl.mvdr.adventofcode.adventofcode2019.day10;

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
        Point result = asteroidField.vaporiseAsteroids(200);
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
