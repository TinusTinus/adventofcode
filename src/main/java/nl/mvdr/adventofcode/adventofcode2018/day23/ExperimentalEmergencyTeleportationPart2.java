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
        
        Set<Point> candidates = getCandidates(nanobots);
        
        long maxNanobotsInRange = candidates.stream()
                .mapToLong(point -> nanobotsInRange(point, nanobots))
                .max()
                .getAsLong();
        
        Point startingPosition = new Point(0, 0, 0);
        int result = candidates.stream()
                .filter(candidate -> nanobotsInRange(candidate, nanobots) == maxNanobotsInRange)
                .mapToInt(startingPosition::manhattanDistance)
                .min()
                .getAsInt();
        
        return "" + result;
    }

    /**
     * Generates a set of points, which are candidate optimal teleportation locations.
     * 
     * @param nanobots all nanobots
     * @return set of points
     */
    private Set<Point> getCandidates(Set<Nanobot> nanobots) {
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
        
        Set<Point> candidates = new HashSet<>();
        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                for (int z = minZ; z <= maxZ; z++) {
                    candidates.add(new Point(x, y, z));
                }
            }
        }
        return candidates;
    }
    
    /**
     * Determines, given a location, which nanobots are in range of this location.
     * 
     * @param point location
     * @param nanobots nanobots
     * @return number of nanobots in range
     */
    private long nanobotsInRange(Point point, Set<Nanobot> nanobots) {
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
