package nl.mvdr.adventofcode.adventofcode2019.day22;

import java.util.List;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.solver.IntSolver;

/**
 * Solution to the day 22 puzzle of 2019's Advent of Code:
 * <a href="https://adventofcode.com/2019/day/22">Slam Shuffle</a>.
 *
 * @author Martijn van de Rijdt
 */
public class SlamShufflePart1 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(SlamShufflePart1.class);

    /**
     * {@inheritDoc}
     * 
     * @return position of card 2019, after shuffling a factory order deck of 10007 cards 
     */
    @Override
    public int solve(Stream<String> lines) {
        Process process = Process.parse(lines);
        List<Integer> shuffledDeck = process.performOnFactoryOrderDeck(10007);
        return shuffledDeck.indexOf(Integer.valueOf(2019));
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        SlamShufflePart1 instance = new SlamShufflePart1();

        String result = instance.solve("input-day22-2019.txt");

        LOGGER.info(result);
    }
}
 