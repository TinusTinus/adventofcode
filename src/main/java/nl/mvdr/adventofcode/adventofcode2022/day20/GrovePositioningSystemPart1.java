package nl.mvdr.adventofcode.adventofcode2022.day20;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.LongSolver;

/**
 * Solution to <a href="https://adventofcode.com/2022/day/20">Grove Positioning System</a>.
 *
 * @author Martijn van de Rijdt
 */
public class GrovePositioningSystemPart1 implements LongSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(GrovePositioningSystemPart1.class);

    @Override
    public long solve(Stream<String> lines) {
        Arrangement arrangement = Arrangement.parse(lines);
        Arrangement mixed = arrangement.mix();
        return mixed.findGroveCoordinates();
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        var instance = new GrovePositioningSystemPart1();

        var result = instance.solve("input-day20-2022.txt");

        LOGGER.info(result);
    }
}
 