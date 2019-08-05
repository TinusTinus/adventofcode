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
public class ExperimentalEmergencyTeleportationPart2 implements PathSolver<Integer> {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ExperimentalEmergencyTeleportationPart2.class);
    
    @Override
    public Integer solve(Path inputFilePath) throws IOException {
        Set<Nanobot> nanobots = Nanobot.parse(inputFilePath);
        
        // Some notes:
        // * there is 1 bot with 962 other bots in its range: pos=<29463738,37565122,55842905>, r=97249189.
        // * there is 1 bot in range of 859 others: pos=<29463738,37565122,55842905>, r=97249189 (the same one as above)
        // * the strongest nanobot is a different one: pos=<16550473,27374441,-19147897>, r=99846219
        // * there are points in range of 890 bots

        // TODO compute the result
        int minimumManhattanDistance = 0;
        
        return Integer.valueOf(minimumManhattanDistance);
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
