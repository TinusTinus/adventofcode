package nl.mvdr.adventofcode.adventofcode2017.day15;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.PathSolver;

/**
 * Solution to the day 15 puzzle of 2017's Advent of Code:
 * <a href="https://adventofcode.com/2017/day/15">Dueling Generators</a>.
 *
 * @author Martijn van de Rijdt
 */
public class DuelingGeneratorsPart1 implements PathSolver<Long> {

    private static final long FACTOR_A = 16_807L;
    private static final long FACTOR_B = 48_271L;
    
    private static final int SAMPLE_SIZE = 40_000_000;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(DuelingGeneratorsPart1.class);
    
    /**
     * {@inheritDoc}
     * 
     * @return the judge's final count
     */
    @Override
    public Long solve(Path inputFilePath) throws IOException {
        List<Long> startValues = Files.lines(inputFilePath)
            .filter(Objects::nonNull)
            .filter(line -> !line.isBlank())
            // Drop the prefix
            .map(line -> line.substring("Generator X starts with ".length()))
            .map(Long::valueOf)
            .collect(Collectors.toList());
        long startValueA = startValues.get(0).longValue();
        long startValueB = startValues.get(1).longValue();
        
        Generator generatorA = new Generator(FACTOR_A, startValueA);
        Generator generatorB = new Generator(FACTOR_B, startValueB);
        
        long result = IntStream.range(0, SAMPLE_SIZE).filter(i -> {
            long a = generatorA.nextValue();
            long b = generatorB.nextValue();
            
            short lowest16BitsA = (short)a;
            short lowest16BitsB = (short)b;
            
            return lowest16BitsA == lowest16BitsB;
        })
        .count();
        
        return Long.valueOf(result);
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        DuelingGeneratorsPart1 instance = new DuelingGeneratorsPart1();

        String result = instance.solve("input-day15-2017.txt");

        LOGGER.info(result);
    }
}
