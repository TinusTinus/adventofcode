package nl.mvdr.adventofcode.adventofcode2023.day09;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.solver.IntSolver;

/**
 * Solution to <a href="https://adventofcode.com/2023/day/9">Mirage Maintenance</a>.
 *
 * @author Martijn van de Rijdt
 */
public class MirageMaintenancePart2 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(MirageMaintenancePart2.class);

    @Override
    public int solve(Stream<String> lines) {
        return lines.map(Sequence::parse)
                .mapToInt(Sequence::extrapolatePreviousValue)
                .sum();
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        var instance = new MirageMaintenancePart2();

        var result = instance.solve("input-day09-2023.txt");

        LOGGER.info(result);
    }
}
 