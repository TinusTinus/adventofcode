package nl.mvdr.adventofcode.adventofcode2020.day11;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.solver.IntSolver;

/**
 * Solution to the day 11 puzzle of 2020's Advent of Code:
 * <a href="https://adventofcode.com/2020/day/11">Seating System</a>.
 *
 * @author Martijn van de Rijdt
 */
public class SeatingSystemPart2 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(SeatingSystemPart2.class);

    /**
     * {@inheritDoc}
     * 
     * @return occupied seats once people stop moving around
     */
    @Override
    public int solve(Stream<String> lines) {
        WaitingArea waitingArea = WaitingArea.parse(lines);
        return waitingArea.performSeating(waitingArea::getClosestVisibleSeats, 5L)
                .people()
                .size();
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        SeatingSystemPart2 instance = new SeatingSystemPart2();

        String result = instance.solve("input-day11-2020.txt");

        LOGGER.info(result);
    }
}
 