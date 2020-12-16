package nl.mvdr.adventofcode.adventofcode2020.day16;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.IntSolver;

/**
 * Solution to the day 16 puzzle of 2020's Advent of Code:
 * <a href="https://adventofcode.com/2020/day/16">Ticket Translation</a>.
 *
 * @author Martijn van de Rijdt
 */
public class TicketTranslationPart1 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(TicketTranslationPart1.class);

    /**
     * {@inheritDoc}
     * 
     * @return the first number which is not the sum of two of the 25 numbers before it
     */
    @Override
    public int solve(Stream<String> lines) {
        PuzzleInput input = PuzzleInput.parse(lines);
        return input.sumInvalidValues();
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        TicketTranslationPart1 instance = new TicketTranslationPart1();

        String result = instance.solve("input-day16-2020.txt");

        LOGGER.info(result);
    }
}
 