package nl.mvdr.adventofcode.adventofcode2022.day02;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.solver.IntSolver;

/**
 * Solution to the day 2 puzzle of 2022's Advent of Code:
 * <a href="https://adventofcode.com/2022/day/02">Rock Paper Scissors</a>.
 *
 * @author Martijn van de Rijdt
 */
public class RockPaperScissorsPart2 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(RockPaperScissorsPart2.class);

    /**
     * {@inheritDoc}
     * 
     * @return score
     */
    @Override
    public int solve(Stream<String> lines) {
        return new RockPaperScissors(Outcome::parse).solve(lines);
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        var instance = new RockPaperScissorsPart2();

        var result = instance.solve("input-day02-2022.txt");

        LOGGER.info(result);
    }
}
 