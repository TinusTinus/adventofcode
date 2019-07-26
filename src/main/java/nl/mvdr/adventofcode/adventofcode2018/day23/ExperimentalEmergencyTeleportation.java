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
public class ExperimentalEmergencyTeleportation implements PathSolver {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ExperimentalEmergencyTeleportation.class);
    
    @Override
    public String solve(Path inputFilePath) throws IOException {
        Set<Nanobot> nanobots = Nanobot.parse(inputFilePath);
        
        LOGGER.info("Nanobots: {}", nanobots); // TODO debug
        
        return null; // TODO
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        ExperimentalEmergencyTeleportation solver = new ExperimentalEmergencyTeleportation();
        String solution = solver.solve("input-day23-2018.txt");
        LOGGER.info(solution);
    }
}
