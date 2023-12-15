package nl.mvdr.adventofcode.adventofcode2023.day15;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.IntSolver;

/**
 * Solution to <a href="https://adventofcode.com/2023/day/15">Lens Library</a>.
 *
 * @author Martijn van de Rijdt
 */
public class LensLibraryPart1 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(LensLibraryPart1.class);

    @Override
    public int solve(Stream<String> lines) {
        var string = lines.collect(Collectors.joining()); // ignore any newlines
        var parts = string.split(",");
        return Stream.of(parts)
                .mapToInt(HashAlgorithm::hash)
                .sum();
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        var instance = new LensLibraryPart1();

        var result = instance.solve("input-day15-2023.txt");

        LOGGER.info(result);
    }
}
 