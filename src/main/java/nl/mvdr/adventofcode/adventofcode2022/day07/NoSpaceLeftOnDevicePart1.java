package nl.mvdr.adventofcode.adventofcode2022.day07;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.solver.IntSolver;

/**
 * Solution to <a href="https://adventofcode.com/2022/day/07">No Space Left On Device</a>.
 *
 * @author Martijn van de Rijdt
 */
public class NoSpaceLeftOnDevicePart1 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(NoSpaceLeftOnDevicePart1.class);
    
    @Override
    public int solve(Stream<String> linesStream) {
        List<String> lines = linesStream.collect(Collectors.toList());
        Directory root = Directory.parse(lines);
        return root.sum();
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        var instance = new NoSpaceLeftOnDevicePart1();

        var result = instance.solve("input-day07-2022.txt");

        LOGGER.info(result);
    }
}
 