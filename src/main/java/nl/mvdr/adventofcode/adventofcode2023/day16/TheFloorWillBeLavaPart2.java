package nl.mvdr.adventofcode.adventofcode2023.day16;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.LongSolver;

/**
 * Solution to <a href="https://adventofcode.com/2023/day/16">The Floor Will Be Lava</a>.
 *
 * @author Martijn van de Rijdt
 */
public class TheFloorWillBeLavaPart2 implements LongSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(TheFloorWillBeLavaPart2.class);

    @Override
    public long solve(Stream<String> lines) {
        var contraption = Contraption.parse(lines.toList());
        return contraption.maxEnergizedTiles();
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        var instance = new TheFloorWillBeLavaPart2();

        var result = instance.solve("input-day16-2023.txt");

        LOGGER.info(result);
    }
}
 