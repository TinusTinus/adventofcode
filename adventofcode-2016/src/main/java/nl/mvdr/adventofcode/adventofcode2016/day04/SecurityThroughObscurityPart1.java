package nl.mvdr.adventofcode.adventofcode2016.day04;

import java.util.function.Predicate;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.solver.IntSolver;

/**
 * Solution to the day 4 puzzle of 2016's Advent of Code:
 * <a href="https://adventofcode.com/2016/day/4">Security Through Obscurity</a>.
 *
 * @author Martijn van de Rijdt
 */
public class SecurityThroughObscurityPart1 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityThroughObscurityPart1.class);
    
    /**
     * {@inheritDoc}
     * 
     * @return sum of the sector IDs of the real rooms
     */
    @Override
    public int solve(Stream<String> lines) {
        return lines.filter(Predicate.not(String::isEmpty))
                .map(Room::parseRoom)
                .filter(Room::isReal)
                .mapToInt(Room::getSectorId)
                .sum();
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        SecurityThroughObscurityPart1 instance = new SecurityThroughObscurityPart1();

        String result = instance.solve("input-day04-2016.txt");

        LOGGER.info(result);
    }
}
