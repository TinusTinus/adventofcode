package nl.mvdr.adventofcode.adventofcode2022.day02;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.IntSolver;

/**
 * Solution to the day 2 puzzle of 2022's Advent of Code:
 * <a href="https://adventofcode.com/2022/day/02">Rock Paper Scissors</a>.
 *
 * @author Martijn van de Rijdt
 */
public class RockPaperScissorsPart1 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(RockPaperScissorsPart1.class);

    /**
     * {@inheritDoc}
     * 
     * @return score
     */
    @Override
    public int solve(Stream<String> lines) {
        return new RockPaperScissors(Round::parsePart1).solve(lines);
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        RockPaperScissorsPart1 instance = new RockPaperScissorsPart1();

        String result = instance.solve("input-day02-2022.txt");

        LOGGER.info(result);
    }
}
 