package nl.mvdr.adventofcode.adventofcode2018.day23;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Comparator;
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
public class ExperimentalEmergencyTeleportationPart2 implements PathSolver<Integer> {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ExperimentalEmergencyTeleportationPart2.class);
    
    @Override
    public Integer solve(Path inputFilePath) throws IOException {
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
        
        Set<Point> candidates = new HashSet<>();
        int stepSize = maxX - minX;
        long maxNanobotsInRange = 0L;
        
        // Note: the following is an approximation, it is not guaranteed to find the correct result
        
        do {
            stepSize = Math.max(stepSize / 2, 1);

            LOGGER.debug("----- Starting step size {} -----", Integer.valueOf(stepSize));
            LOGGER.debug("x: {} - {}", Integer.valueOf(minX), Integer.valueOf(maxX));
            LOGGER.debug("y: {} - {}", Integer.valueOf(minY), Integer.valueOf(maxY));
            LOGGER.debug("z: {} - {}", Integer.valueOf(minZ), Integer.valueOf(maxZ));
            
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
            
            Point bestCandidate = candidates.stream()
                    .min(Comparator.comparing(startingPosition::manhattanDistance))
                    .get();
            
            minX = bestCandidate.getX() - stepSize;
            maxX = bestCandidate.getX() + stepSize;
            minY = bestCandidate.getY() - stepSize;
            maxY = bestCandidate.getY() + stepSize;
            minZ = bestCandidate.getZ() - stepSize;
            maxZ = bestCandidate.getZ() + stepSize;
            
        } while (1 < stepSize);
        
        int minimumManhattanDistance = candidates.stream()
                .mapToInt(startingPosition::manhattanDistance)
                .min()
                .getAsInt();
        
        return Integer.valueOf(minimumManhattanDistance);
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
