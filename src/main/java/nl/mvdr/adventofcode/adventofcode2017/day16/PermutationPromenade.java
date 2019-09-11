package nl.mvdr.adventofcode.adventofcode2017.day16;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.PathSolver;

/**
 * Solution to the day 16 puzzle of 2017's Advent of Code:
 * <a href="https://adventofcode.com/2017/day/16">Permutation Promenade</a>.
 *
 * @author Martijn van de Rijdt
 */
abstract class PermutationPromenade implements PathSolver<String> {

    private static final Logger LOGGER = LoggerFactory.getLogger(PermutationPromenade.class);

    /** The number of times the dance should be performed. */
    private final int iterations;
    
    /** The number of programs performing the dance. */
    private final int numberOfPrograms;

    /**
     * Constructor.
     * 
     * @param iterations number of times the dance should be performed
     */
    PermutationPromenade(int iterations) {
        this(iterations, 16);
    }

    /**
     * Constructor.
     * 
     * @param iterations number of times the dance should be performed
     * @param numberOfPrograms number of programs performing the dance
     */
    PermutationPromenade(int iterations, int numberOfPrograms) {
        super();
        this.iterations = iterations;
        this.numberOfPrograms = numberOfPrograms;
    }

    /**
     * {@inheritDoc}
     * 
     * @return order of the programs after completing their dance
     */
    @Override
    public String solve(Path inputFilePath) throws IOException {
        List<DanceMove> moves = DanceMove.parse(inputFilePath);

        List<Program> dancers = IntStream.range(0, numberOfPrograms).map(i -> 'a' + i)
                .mapToObj(c -> new Program((char) c)).collect(Collectors.toList());

        for (int i = 0; i != iterations; i++) { 
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("{} dances completed: {}", Integer.valueOf(i), toString(dancers));
            }
            
            for (DanceMove move : moves) {
                dancers = move.perform(dancers);
            }
        }

        return toString(dancers);
    }
    
    private String toString(List<Program> dancers) {
        return dancers.stream().map(Object::toString).collect(Collectors.joining());
    }
}
