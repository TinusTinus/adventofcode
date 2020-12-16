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
public class TicketTranslationPart2 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(TicketTranslationPart2.class);

    /**
     * {@inheritDoc}
     * 
     * @return product of the values of the six departure fields on our ticket
     */
    @Override
    public int solve(Stream<String> lines) {
        PuzzleInput input = PuzzleInput.parse(lines);
        return input.multiplyDepartureFields();
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        TicketTranslationPart2 instance = new TicketTranslationPart2();

        String result = instance.solve("input-day16-2020.txt");

        LOGGER.info(result);
    }
}
 