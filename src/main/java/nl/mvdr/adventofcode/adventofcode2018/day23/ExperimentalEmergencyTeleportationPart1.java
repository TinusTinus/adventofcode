package nl.mvdr.adventofcode.adventofcode2018.day23;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Comparator;
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
public class ExperimentalEmergencyTeleportationPart1 implements PathSolver<Long> {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ExperimentalEmergencyTeleportationPart1.class);
    
    @Override
    public Long solve(Path inputFilePath) throws IOException {
        Set<Nanobot> nanobots = Nanobot.parse(inputFilePath);
        
        LOGGER.debug("Nanobots: {}", nanobots);
        
        Nanobot strongestNanobot = nanobots.stream()
                .max(Comparator.comparing(Nanobot::getRadius))
                .get();
        
        long nanobotsInRange = nanobots.stream()
                .map(Nanobot::getPosition)
                .filter(strongestNanobot::inRange)
                .count();
        
        return Long.valueOf(nanobotsInRange);
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        ExperimentalEmergencyTeleportationPart1 solver = new ExperimentalEmergencyTeleportationPart1();
        String solution = solver.solve("input-day23-2018.txt");
        LOGGER.info(solution);
    }
}
