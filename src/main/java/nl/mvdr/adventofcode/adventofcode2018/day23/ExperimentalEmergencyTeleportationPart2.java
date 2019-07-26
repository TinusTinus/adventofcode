package nl.mvdr.adventofcode.adventofcode2018.day23;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.PathSolver;

/**
 * Solution to the day 20 puzzle of 2018's Advent of Code:
 * <a href="https://adventofcode.com/2018/day/23">Experimental Emergency Teleportation</a>.
 *
 * @author Martijn van de Rijdt
 */
public class ExperimentalEmergencyTeleportationPart2 implements PathSolver {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ExperimentalEmergencyTeleportationPart2.class);
    
    @Override
    public String solve(Path inputFilePath) throws IOException {
        // TODO This solution is technically correct, but does not perform.
        
        Set<Nanobot> nanobots = Nanobot.parse(inputFilePath);
        
        long maxNanobotsInRange = 0L;
        int minimumManhattanDistance = Integer.MAX_VALUE;
        
        Set<Point> candidates = nanobots.parallelStream()
                .flatMap(nanobot -> nanobot.range().stream())
                .collect(Collectors.toSet());
        
        Point startingPosition = new Point(0, 0, 0);
        
        for (Point point : candidates) {
            long nanobotsInRange = countNanobotsInRange(point, nanobots);
            if (maxNanobotsInRange < nanobotsInRange) {
                maxNanobotsInRange = nanobotsInRange;
                minimumManhattanDistance = startingPosition.manhattanDistance(point);
                LOGGER.debug(
                        "New maximum nanobots in range found: {}, at {}, Manhattan distance from starting point: {}",
                        Long.valueOf(maxNanobotsInRange), point, Integer.valueOf(minimumManhattanDistance));
            } else if (maxNanobotsInRange == nanobotsInRange) {
                int manhattanDistance = startingPosition.manhattanDistance(point);
                if (manhattanDistance < minimumManhattanDistance) {
                    minimumManhattanDistance = manhattanDistance;
                    LOGGER.debug("New shortest Manhattan distance found at {}: {}", point,
                            Integer.valueOf(manhattanDistance));
                }
            }
        }
        
        return "" + minimumManhattanDistance;
    }

    /**
     * Determines, given a location, which nanobots are in range of this location.
     * 
     * @param point location
     * @param nanobots nanobots
     * @return number of nanobots in range
     */
    private long countNanobotsInRange(Point point, Set<Nanobot> nanobots) {
        return nanobots.stream()
                .filter(nanobot -> nanobot.inRange(point))
                .count();
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        ExperimentalEmergencyTeleportationPart2 solver = new ExperimentalEmergencyTeleportationPart2();
        String solution = solver.solve("input-day23-2018.txt");
        LOGGER.info(solution);
    }
}
