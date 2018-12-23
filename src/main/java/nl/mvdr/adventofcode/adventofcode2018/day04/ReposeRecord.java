package nl.mvdr.adventofcode.adventofcode2018.day04;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

import nl.mvdr.adventofcode.PathSolver;

/**
 * Solution to the day 4 puzzle of 2018's Advent of Code:
 * <a href="https://adventofcode.com/2018/day/4">Repose Record</a>.
 *
 * @author Martijn van de Rijdt
 */
public class ReposeRecord implements PathSolver {

    @Override
    public String solve(Path inputFilePath) throws IOException {
        Files.lines(inputFilePath)
                // ignore empty lines (the last line in the file)
                .filter(Objects::nonNull)
                .filter(line -> !line.isBlank())
                // sort input in chronological order (which is equal to lexicographical order)
                .sorted()
                .forEach(System.out::println);
        
        
        // TODO implement
        return null;
    }

        /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        ReposeRecord instance = new ReposeRecord();

        String result = instance.solve("input-day04-2018.txt");

        System.out.println(result);
    }
}
