package nl.mvdr.adventofcode.adventofcode2017.day02;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.solver.IntSolver;

/**
 * Solution to the day 2 puzzle of 2017's Advent of Code:
 * <a href="https://adventofcode.com/2017/day/2">Corruption Checksum</a>.
 *
 * @author Martijn van de Rijdt
 */
public class CorruptionChecksumPart1 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(CorruptionChecksumPart1.class);
    
    @Override
    public int solve(Stream<String> lines) {
        return lines.filter(Predicate.not(String::isBlank))
                // parse each line to a list of numbers
                .map(this::parseLine)
                .mapToInt(numbers -> max(numbers) - min(numbers))
                .sum();
    }
    
    /**
     * Parses the given line from the puzzle input into a list of numbers.
     * 
     * @param line line to parse
     * @return numbers on this line
     */
    private List<Integer> parseLine(String line) {
        return Stream.of(line.split("\\s"))
                .map(Integer::valueOf)
                .collect(Collectors.toList());
    }
    
    private int max(List<Integer> numbers) {
        return numbers.stream()
                .mapToInt(Integer::intValue)
                .max()
                .orElseThrow();
    }
    
    private int min(List<Integer> numbers) {
        return numbers.stream()
                .mapToInt(Integer::intValue)
                .min()
                .orElseThrow();
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        CorruptionChecksumPart1 instance = new CorruptionChecksumPart1();

        String result = instance.solve("input-day02-2017.txt");

        LOGGER.info(result);
    }
}
