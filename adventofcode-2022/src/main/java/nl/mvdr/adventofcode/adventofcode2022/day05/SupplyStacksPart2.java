package nl.mvdr.adventofcode.adventofcode2022.day05;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.solver.LinesSolver;

/**
 * Solution to <a href="https://adventofcode.com/2022/day/05">Supply Stacks</a>.
 *
 * @author Martijn van de Rijdt
 */
public class SupplyStacksPart2 implements LinesSolver<String> {

    private static final Logger LOGGER = LoggerFactory.getLogger(SupplyStacksPart2.class);

    @Override
    public String solve(Stream<String> lines) {
        SupplyStacks solver = new SupplyStacks(Crane.CRATE_MOVER_9001);
        return solver.solve(lines);
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        var instance = new SupplyStacksPart2();

        var result = instance.solve("input-day05-2022.txt");

        LOGGER.info(result);
    }
}
 