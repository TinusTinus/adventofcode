package nl.mvdr.adventofcode.adventofcode2017.day02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.PathSolver;

/**
 * Solution to the day 2 puzzle of 2017's Advent of Code:
 * <a href="https://adventofcode.com/2017/day/2">Corruption Checksum</a>.
 *
 * @author Martijn van de Rijdt
 */
public class CorruptionChecksumPart2 implements PathSolver<Integer> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CorruptionChecksumPart2.class);
    
    @Override
    public Integer solve(Path inputFilePath) throws IOException {
        int result = Files.lines(inputFilePath)
                // ignore empty lines (the last line in the file)
                .filter(Objects::nonNull)
                .filter(line -> !line.isBlank())
                // parse each line to a list of numbers
                .map(this::parseLine)
                .mapToInt(this::getEvenDivision)
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
    
    /**
     * Gets the value of the even division between two of the given numbers.
     * 
     * There must be (exactly) one such division.
     * 
     * @param numbers numbers on a line
     * @return value of the even division
     */
    private int getEvenDivision(List<Integer> numbers) {
        OptionalInt result = OptionalInt.empty();
        int i = 0;
        while (result.isEmpty()) {
            int iValue = numbers.get(i).intValue();
            for (int j = 0; j != numbers.size(); j++) {
                if (j != i) {
                    int jValue = numbers.get(j).intValue();
                    if (iValue % jValue == 0) {
                        result = OptionalInt.of(iValue / jValue);
                    }
                }
            }
            i++;
        }
        return result.getAsInt();
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        CorruptionChecksumPart2 instance = new CorruptionChecksumPart2();

        String result = instance.solve("input-day02-2017.txt");

        LOGGER.info(result);
    }
}
