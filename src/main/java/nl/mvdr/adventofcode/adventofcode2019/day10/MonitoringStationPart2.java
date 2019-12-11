package nl.mvdr.adventofcode.adventofcode2019.day10;

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
public class MonitoringStationPart2 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(MonitoringStationPart2.class);
    
    /**
     * {@inheritDoc}
     * 
     * @return identification of the 200th asteroid to be completely vaporised by a giant laser
     */
    @Override
    public int solve(Stream<String> lines) {
        Set<Point> asteroids = parse(lines);
        
        return 0; // TODO
    }

    /**
     * Parses the contents of the input into a set of asteroid locations.
     * 
     * @param linesStream puzzle input
     * @return locations of the asteroids in the puzzle input
     */
    private Set<Point> parse(Stream<String> linesStream) {
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
        return Set.copyOf(asteroids);
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
