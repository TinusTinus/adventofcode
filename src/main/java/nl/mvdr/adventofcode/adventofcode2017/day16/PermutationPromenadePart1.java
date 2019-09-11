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
public class PermutationPromenadePart1 implements PathSolver<String> {

    private static final Logger LOGGER = LoggerFactory.getLogger(PermutationPromenadePart1.class);
    
    /** The number of programs performing the dance. */
    private final int numberOfPrograms;
    
    /** Constructor. */
    public PermutationPromenadePart1() {
        this(16);
    }
    
    /**
     * Constructor.
     * 
     * @param numberOfPrograms number of programs performing the dance
     */
    PermutationPromenadePart1(int numberOfPrograms) {
        super();
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
        
        List<Program> dancers = IntStream.range(0, numberOfPrograms)
                .map(i -> 'a' + i)
                .mapToObj(c -> new Program((char) c))
                .collect(Collectors.toList());
        
        for (DanceMove move : moves) {
            dancers = move.perform(dancers);
        }
        
        return dancers.stream()
                .map(Object::toString)
                .collect(Collectors.joining());
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        PermutationPromenadePart1 instance = new PermutationPromenadePart1();

        String result = instance.solve("input-day16-2017.txt");

        LOGGER.info(result);
    }
}
