package nl.mvdr.adventofcode.adventofcode2018.day23;

import java.util.Set;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.LongSolver;

/**
 * Solution to the day 23 puzzle of 2018's Advent of Code:
 * <a href="https://adventofcode.com/2018/day/23">Experimental Emergency Teleportation</a>.
 *
 * @author Martijn van de Rijdt
 */
public class ExperimentalEmergencyTeleportationPart1 implements LongSolver {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ExperimentalEmergencyTeleportationPart1.class);
    
    @Override
    public long solve(Stream<String> lines) {
        Set<Nanobot> nanobots = Nanobot.parse(lines);
        
        LOGGER.debug("Nanobots: {}", nanobots);
        
        Nanobot strongestNanobot = Nanobot.strongest(nanobots);
        
        return strongestNanobot.botsInRange(nanobots);
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
