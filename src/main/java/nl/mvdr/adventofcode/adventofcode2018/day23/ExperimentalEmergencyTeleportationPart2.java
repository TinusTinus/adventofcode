package nl.mvdr.adventofcode.adventofcode2018.day23;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Set;

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
        
        Point startingPosition = new Point(0, 0, 0);
        
        int minX = nanobots.stream()
                .map(Nanobot::getPosition)
                .mapToInt(Point::getX)
                .min()
                .getAsInt();
        int maxX = nanobots.stream()
                .map(Nanobot::getPosition)
                .mapToInt(Point::getX)
                .max()
                .getAsInt();
        int minY = nanobots.stream()
                .map(Nanobot::getPosition)
                .mapToInt(Point::getY)
                .min()
                .getAsInt();
        int maxY = nanobots.stream()
                .map(Nanobot::getPosition)
                .mapToInt(Point::getY)
                .max()
                .getAsInt();
        int minZ = nanobots.stream()
                .map(Nanobot::getPosition)
                .mapToInt(Point::getZ)
                .min()
                .getAsInt();
        int maxZ = nanobots.stream()
                .map(Nanobot::getPosition)
                .mapToInt(Point::getZ)
                .max()
                .getAsInt();
        
        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                for (int z = minZ; z <= maxZ; z++) {
                    Point point = new Point(x, y, z);
                    long nanobotsInRange = countNanobotsInRange(point, nanobots);
                    if (maxNanobotsInRange < nanobotsInRange) {
                        maxNanobotsInRange = nanobotsInRange;
                        minimumManhattanDistance = startingPosition.manhattanDistance(point);
                        LOGGER.debug("New maximum nanobots in range found: {}, at {}, Manhattan distance from starting point: {}",
                                Long.valueOf(maxNanobotsInRange), point, Integer.valueOf(minimumManhattanDistance));
                    } else if (maxNanobotsInRange == nanobotsInRange) {
                        int manhattanDistance = startingPosition.manhattanDistance(point);
                        if (manhattanDistance < minimumManhattanDistance) {
                            minimumManhattanDistance = manhattanDistance;
                            LOGGER.debug("New shortest Manhattan distance found at {}: {}", point, Integer.valueOf(manhattanDistance));
                        }
                    }
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
