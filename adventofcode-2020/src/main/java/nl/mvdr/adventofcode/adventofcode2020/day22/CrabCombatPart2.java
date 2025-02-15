package nl.mvdr.adventofcode.adventofcode2020.day22;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.solver.IntSolver;

/**
 * Solution to the day 22 puzzle of 2020's Advent of Code:
 * <a href="https://adventofcode.com/2020/day/22">Crab Combat</a>.
 *
 * @author Martijn van de Rijdt
 */
public class CrabCombatPart2 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(CrabCombatPart2.class);

    /**
     * {@inheritDoc}
     * 
     * @return the winning player's score
     */
    @Override
    public int solve(Stream<String> lines) {
        return Game.parse(lines, true).play().winnerScore();
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        CrabCombatPart2 instance = new CrabCombatPart2();

        String result = instance.solve("input-day22-2020.txt");

        LOGGER.info(result);
    }
}
 