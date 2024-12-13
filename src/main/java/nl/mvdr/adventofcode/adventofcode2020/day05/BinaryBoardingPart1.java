package nl.mvdr.adventofcode.adventofcode2020.day05;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.solver.IntSolver;

/**
 * Solution to the day 5 puzzle of 2020's Advent of Code:
 * <a href="https://adventofcode.com/2020/day/5">Binary Boarding</a>.
 *
 * @author Martijn van de Rijdt
 */
public class BinaryBoardingPart1 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(BinaryBoardingPart1.class);

    /**
     * {@inheritDoc}
     * 
     * @return highest seat id
     */
    @Override
    public int solve(Stream<String> lines) {
        return BoardingPass.parse(lines).stream()
                .mapToInt(BoardingPass::seatId)
                .max()
                .orElseThrow();
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        BinaryBoardingPart1 instance = new BinaryBoardingPart1();

        String result = instance.solve("input-day05-2020.txt");

        LOGGER.info(result);
    }
}
 