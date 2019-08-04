package nl.mvdr.adventofcode.adventofcode2018.day23;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
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
public class ExperimentalEmergencyTeleportationPart2 implements PathSolver<Integer> {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ExperimentalEmergencyTeleportationPart2.class);
    
    @Override
    public Integer solve(Path inputFilePath) throws IOException {
        Set<Nanobot> nanobots = Nanobot.parse(inputFilePath);
        
        // Figure out, for each bot, how many other bots are in its range.
        Map<Nanobot, Long> intersections = nanobots.stream()
                .collect(Collectors.toMap(Function.identity(), bot -> bot.botsInRange(nanobots)));
        LOGGER.info("Maximum number of bots in range: {}", intersections.values().stream()
                .mapToLong(Long::longValue)
                .max()
                .getAsLong());
        
        // Result of this is 962.
        // So 962 is an upper bound of the maximum number of bots.
        // It may be a lower bound as well...?
        
        // TODO
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
