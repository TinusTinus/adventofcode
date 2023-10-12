package nl.mvdr.adventofcode.adventofcode2022.day23;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.IntSolver;
import nl.mvdr.adventofcode.point.Point;

/**
 * Solution to <a href="https://adventofcode.com/2022/day/23">Unstable Diffusion</a>.
 *
 * @author Martijn van de Rijdt
 */
public class UnstableDiffusionPart1 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(UnstableDiffusionPart1.class);

    
    /**
     * Parses the puzzle input into a set of initial elf positions.
     * 
     * @param lines puzzle input
     * @return set containing elf positions
     */
    private static Set<Point> parseInput(List<String> lines) {
        Set<Point> result = new HashSet<>();
        for (int y = 0; y != lines.size(); y++) {
            var line = lines.get(y);
            for (int x = 0; x != line.length(); x++) {
                char c = line.charAt(x);
                if (c == '#') {
                    // Elf found
                    result.add(new Point(x, y));
                } else if (c != '.') {
                    throw new IllegalArgumentException("Unexpected input: " + c);
                }
            }
        }
        return result;
    }
    
    @Override
    public int solve(Stream<String> lines) {
        Set<Point> elves = parseInput(lines.toList());
        
        return 0; // TODO
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        var instance = new UnstableDiffusionPart1();

        var result = instance.solve("input-day23-2022.txt");

        LOGGER.info(result);
    }
}
 