package nl.mvdr.adventofcode.adventofcode2023.day08;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.solver.LongSolver;

/**
 * Solution to <a href="https://adventofcode.com/2023/day/8">Haunted Wasteland</a>.
 *
 * @author Martijn van de Rijdt
 */
public class HauntedWastelandPart1 implements LongSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(HauntedWastelandPart1.class);

    @Override
    public long solve(Stream<String> lines) {
        var map = DesertMap.parse(lines.toList());
        return map.computePathLength();
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        var instance = new HauntedWastelandPart1();

        var result = instance.solve("input-day08-2023.txt");

        LOGGER.info(result);
    }
}
 