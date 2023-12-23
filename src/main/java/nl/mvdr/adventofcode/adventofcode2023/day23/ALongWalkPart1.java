package nl.mvdr.adventofcode.adventofcode2023.day23;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.IntSolver;

/**
 * Solution to <a href="https://adventofcode.com/2023/day/23">A Long Walk</a>.
 *
 * @author Martijn van de Rijdt
 */
public class ALongWalkPart1 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(ALongWalkPart1.class);

    @Override
    public int solve(Stream<String> lines) {
        var map = HikingTrailsMap.parse(lines.toList());
        var hike = new Hike(map);
        return hike.longestHikeLength().orElseThrow(() -> new IllegalArgumentException("No path found to the goal"));
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        var instance = new ALongWalkPart1();

        var result = instance.solve("input-day23-2023.txt");

        LOGGER.info(result);
    }
}
 