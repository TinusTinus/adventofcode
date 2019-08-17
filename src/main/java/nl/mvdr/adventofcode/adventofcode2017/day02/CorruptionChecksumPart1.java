package nl.mvdr.adventofcode.adventofcode2017.day02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.PathSolver;

/**
 * Solution to the day 2 puzzle of 2018's Advent of Code:
 * <a href="https://adventofcode.com/2018/day/2">Corruption Checksum</a>.
 *
 * @author Martijn van de Rijdt
 */
public class CorruptionChecksumPart1 implements PathSolver<Integer> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CorruptionChecksumPart1.class);
    
    @Override
    public Integer solve(Path inputFilePath) throws IOException {
        int result = Files.lines(inputFilePath)
                // ignore empty lines (the last line in the file)
                .filter(Objects::nonNull)
                .filter(line -> !line.isBlank())
                // parse each line to a list of numbers
                .map(this::parseLine)
                .mapToInt(numbers -> max(numbers) - min(numbers))
                .sum();
                
        
        return Integer.valueOf(result);
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
                .getAsInt();
    }
    
    private int min(List<Integer> numbers) {
        return numbers.stream()
                .mapToInt(Integer::intValue)
                .min()
                .getAsInt();
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
