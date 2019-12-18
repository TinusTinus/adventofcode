package nl.mvdr.adventofcode.adventofcode2019.day19;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.IntSolver;

/**
 * Solution to the day 19 puzzle of 2019's Advent of Code:
 * <a href="https://adventofcode.com/2019/day/19">Many-Worlds Interpretation</a>.
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
        return 0; // TODO
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        ManyWorldsInterpretationPart1 instance = new ManyWorldsInterpretationPart1();

        String result = instance.solve("input-day19-2019.txt");

        LOGGER.info(result);
    }
}
 