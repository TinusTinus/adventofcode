package nl.mvdr.adventofcode.adventofcode2020.day02;

import java.util.function.Predicate;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.LongSolver;

/**
 * Solution to the day 2 puzzle of 2020's Advent of Code:
 * <a href="https://adventofcode.com/2020/day/2">Password Philosophy</a>.
 *
 * @author Martijn van de Rijdt
 */
public class PasswordPhilosophyPart1 implements LongSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(PasswordPhilosophyPart1.class);

    /**
     * {@inheritDoc}
     * 
     * @return product of the two entries that sum to 2020 
     */
    @Override
    public long solve(Stream<String> lines) {
        return lines.filter(Predicate.not(String::isEmpty))
                .map(PasswordEntry::parse)
                .filter(PasswordEntry::isValid)
                .count();
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        PasswordPhilosophyPart1 instance = new PasswordPhilosophyPart1();

        String result = instance.solve("input-day02-2020.txt");

        LOGGER.info(result);
    }
}
 