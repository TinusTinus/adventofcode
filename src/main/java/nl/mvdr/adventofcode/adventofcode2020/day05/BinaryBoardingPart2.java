package nl.mvdr.adventofcode.adventofcode2020.day05;

import java.util.Set;
import java.util.stream.Collectors;
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
public class BinaryBoardingPart2 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(BinaryBoardingPart2.class);

    /**
     * {@inheritDoc}
     * 
     * @return seat id
     */
    @Override
    public int solve(Stream<String> lines) {
        Set<Integer> seatIds = BoardingPass.parse(lines).stream()
                .mapToInt(BoardingPass::seatId)
                .boxed()
                .collect(Collectors.toSet());
        
        return seatIds.stream()
                .filter(i -> !seatIds.contains(Integer.valueOf(i.intValue() + 1)))
                .filter(i -> seatIds.contains(Integer.valueOf(i.intValue() + 2)))
                .findFirst()
                .orElseThrow()
                .intValue() + 1;        
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        BinaryBoardingPart2 instance = new BinaryBoardingPart2();

        String result = instance.solve("input-day05-2020.txt");

        LOGGER.info(result);
    }
}
 