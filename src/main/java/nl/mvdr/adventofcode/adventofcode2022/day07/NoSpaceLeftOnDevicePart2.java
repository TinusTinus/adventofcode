package nl.mvdr.adventofcode.adventofcode2022.day07;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.IntSolver;

/**
 * Solution to <a href="https://adventofcode.com/2022/day/07">No Space Left On Device</a>.
 *
 * @author Martijn van de Rijdt
 */
public class NoSpaceLeftOnDevicePart2 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(NoSpaceLeftOnDevicePart2.class);
    
    @Override
    public int solve(Stream<String> linesStream) {
        var lines = linesStream.collect(Collectors.toList());
        var root = Directory.parse(lines);
        
        var unused = 70_000_000 - root.totalSize();
        var toBeFreedUp = 30_000_000 - unused;
        
        return root.min(toBeFreedUp);
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        var instance = new NoSpaceLeftOnDevicePart2();

        var result = instance.solve("input-day07-2022.txt");

        LOGGER.info(result);
    }
}
 