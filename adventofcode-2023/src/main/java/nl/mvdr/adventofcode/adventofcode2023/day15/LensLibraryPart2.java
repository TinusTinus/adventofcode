package nl.mvdr.adventofcode.adventofcode2023.day15;

import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.solver.IntSolver;

/**
 * Solution to <a href="https://adventofcode.com/2023/day/15">Lens Library</a>.
 *
 * @author Martijn van de Rijdt
 */
public class LensLibraryPart2 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(LensLibraryPart2.class);

    @Override
    public int solve(Stream<String> lines) {
        var boxes = IntStream.range(0, HashAlgorithm.NUMBER_OF_VALUES)
                .mapToObj(_ -> new Box())
                .toArray(Box[]::new);

        var string = lines.collect(Collectors.joining()); // ignore any newlines
        var parts = string.split(",");
        Stream.of(parts)
                .map(Step::parse)
                .forEach(step -> step.performStep(boxes));
        
        return Stream.of(boxes)
                .mapToInt(Box::focusingPower)
                .sum();
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        var instance = new LensLibraryPart2();

        var result = instance.solve("input-day15-2023.txt");

        LOGGER.info(result);
    }
}
 