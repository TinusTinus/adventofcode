package nl.mvdr.adventofcode.adventofcode2018.day23;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashSet;
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
        Set<Nanobot> nanobots = Nanobot.parse(inputFilePath);
        
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
        
        Point startingPosition = new Point(0, 0, 0);
        
        Set<Point> candidates;
        int stepSize = maxX - minX;
        long maxNanobotsInRange = 0L;
        
        // Note: the following is an approximation, it is not guaranteed to find the correct result
        
        do {
            LOGGER.debug("x: {} - {}", Integer.valueOf(minX), Integer.valueOf(maxX));
            LOGGER.debug("y: {} - {}", Integer.valueOf(minY), Integer.valueOf(maxY));
            LOGGER.debug("z: {} - {}", Integer.valueOf(minZ), Integer.valueOf(maxZ));
            
            candidates = new HashSet<>();
            stepSize = Math.max(stepSize / 10, 1);
            
            int percentageDone = 0;
            for (int x = minX; x <= maxX; x = x + stepSize) {
                for (int y = minY; y <= maxY; y = y + stepSize) {
                    for (int z = minZ; z <= maxZ; z = z + stepSize) {
                        Point point = new Point(x, y, z);
                        long nanobotsInRange = countNanobotsInRange(point, nanobots);
                        if (maxNanobotsInRange < nanobotsInRange) {
                            maxNanobotsInRange = nanobotsInRange;
                            LOGGER.debug("New maximum nanobots in range found: {}, at {}.", Long.valueOf(maxNanobotsInRange), point);
                            candidates = new HashSet<>();
                            candidates.add(point);
                        } else if (maxNanobotsInRange == nanobotsInRange) {
                            candidates.add(point);
                        }
                    }
                }
                int newPercentage = 100 * (x - minX) / (maxX - minX);
                if (percentageDone != newPercentage) {
                    LOGGER.debug("{}% done, {} candidates found so far.", Integer.valueOf(newPercentage), Integer.valueOf(candidates.size()));
                }
                percentageDone = newPercentage;
            }
            
            LOGGER.debug("Step size {}: {} points found with {} nanobots in range.", Integer.valueOf(stepSize),
                    Integer.valueOf(candidates.size()), Long.valueOf(maxNanobotsInRange));
            
            minX = candidates.stream()
                    .mapToInt(Point::getX)
                    .min()
                    .getAsInt() - stepSize;
            maxX = candidates.stream()
                    .mapToInt(Point::getX)
                    .max()
                    .getAsInt() + stepSize;
            minY = candidates.stream()
                    .mapToInt(Point::getY)
                    .min()
                    .getAsInt() - stepSize;
            maxY = candidates.stream()
                    .mapToInt(Point::getY)
                    .max()
                    .getAsInt() + stepSize;
            minZ = candidates.stream()
                    .mapToInt(Point::getZ)
                    .min()
                    .getAsInt() - stepSize;
            maxZ = candidates.stream()
                    .mapToInt(Point::getZ)
                    .max()
                    .getAsInt() + stepSize;
            
        } while (1 < stepSize);
        
        int minimumManhattanDistance = candidates.stream()
                .mapToInt(startingPosition::manhattanDistance)
                .min()
                .getAsInt();
        
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
