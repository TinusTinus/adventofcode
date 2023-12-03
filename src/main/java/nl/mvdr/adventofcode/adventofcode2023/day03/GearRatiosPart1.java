package nl.mvdr.adventofcode.adventofcode2023.day03;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.IntSolver;

/**
 * Solution to <a href="https://adventofcode.com/2023/day/03">Gear Ratios</a>.
 *
 * @author Martijn van de Rijdt
 */
public class GearRatiosPart1 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(GearRatiosPart1.class);

    @Override
    public int solve(Stream<String> lines) {
        var schematic = EngineSchematic.parse(lines.toList());
        return schematic.sumPartNumbers();
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        var instance = new GearRatiosPart1();

        var result = instance.solve("input-day03-2023.txt");

        LOGGER.info(result);
    }
}
 