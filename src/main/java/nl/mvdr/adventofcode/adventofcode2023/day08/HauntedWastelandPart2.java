package nl.mvdr.adventofcode.adventofcode2023.day08;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.LongSolver;

/**
 * Solution to <a href="https://adventofcode.com/2023/day/8">Haunted Wasteland</a>.
 *
 * @author Martijn van de Rijdt
 */
public class HauntedWastelandPart2 implements LongSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(HauntedWastelandPart2.class);

    @Override
    public long solve(Stream<String> lines) {
        var map = DesertMap.parse(lines.toList());
        return map.computeGhostPathLength();
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        var instance = new HauntedWastelandPart2();

        var result = instance.solve("input-day08-2023.txt");

        LOGGER.info(result);
    }
}
 