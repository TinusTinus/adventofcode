package nl.mvdr.adventofcode.adventofcode2019.day10;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.IntSolver;

/**
 * Solution to the day 10 puzzle of 2019's Advent of Code:
 * <a href="https://adventofcode.com/2019/day/10">Monitoring Station</a>.
 *
 * @author Martijn van de Rijdt
 */
public class MonitoringStationPart1 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(MonitoringStationPart1.class);
    
    /**
     * {@inheritDoc}
     * 
     * @return number of visible asteroids
     */
    @Override
    public int solve(Stream<String> lines) {
        return 0; // TODO
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
