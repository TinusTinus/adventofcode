package nl.mvdr.adventofcode.adventofcode2019.day18;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.IntSolver;

/**
 * Solution to the day 18 puzzle of 2019's Advent of Code:
 * <a href="https://adventofcode.com/2019/day/18">Many-Worlds Interpretation</a>.
 *
 * @author Martijn van de Rijdt
 */
public class ManyWorldsInterpretationPart1 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(ManyWorldsInterpretationPart1.class);

    /**
     * {@inheritDoc}
     * 
     * @return length in steps of the shortest path that collects all of the keys
     */
    @Override
    public int solve(Stream<String> lines) {
        return Vault.parse(lines).shortestPathToPickUpAllKeys();
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        ManyWorldsInterpretationPart1 instance = new ManyWorldsInterpretationPart1();

        String result = instance.solve("input-day18-2019.txt");

        LOGGER.info(result);
    }
}
 