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
    
    private final int targets;
    
    /**
     * Constructor.
     * 
     * @param targets number of asteroids to vaporise
     */
    MonitoringStationPart2(int targets) {
        super();
        this.targets = targets;
    }
    
    /** Constructor. */
    public MonitoringStationPart2() {
        this(200);
    }
    
    /**
     * {@inheritDoc}
     * 
     * @return identification of the last asteroid to be completely vaporised by a giant laser
     */
    @Override
    int solve(AsteroidField asteroidField) {
        Point result = asteroidField.vaporiseAsteroids(targets);
        return result.x() * 100 + result.y();
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
