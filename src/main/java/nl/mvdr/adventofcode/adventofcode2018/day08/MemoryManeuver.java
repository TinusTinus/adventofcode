package nl.mvdr.adventofcode.adventofcode2018.day08;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.PathSolver;

/**
 * Solution to the day 8 puzzle of 2018's Advent of Code:
 * <a href="https://adventofcode.com/2018/day/8">Memory Maneuver</a>.
 *
 * @author Martijn van de Rijdt
 */
public class MemoryManeuver implements PathSolver {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(MemoryManeuver.class);
    
    @Override
    public String solve(Path inputFilePath) throws IOException {
        // All of the input is on the first line of the input.
        String line = Files.lines(inputFilePath).findFirst().get();
        
        // The input consists of a sequence of integers, separated by spaced.
        List<Integer> numbers = Stream.of(line.split(" "))
                .mapToInt(Integer::valueOf)
                .mapToObj(Integer::valueOf)
                .collect(Collectors.toList());
        
        return null; // TODO
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        MemoryManeuver instance = new MemoryManeuver();

        String result = instance.solve("input-day08-2018.txt");

        LOGGER.info(result);
    }
}
