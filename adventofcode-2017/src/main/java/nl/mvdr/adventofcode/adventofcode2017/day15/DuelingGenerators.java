package nl.mvdr.adventofcode.adventofcode2017.day15;

import nl.mvdr.adventofcode.solver.LongSolver;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Solution to the day 15 puzzle of 2017's Advent of Code:
 * <a href="https://adventofcode.com/2017/day/15">Dueling Generators</a>.
 *
 * @author Martijn van de Rijdt
 */
abstract class DuelingGenerators implements LongSolver {

    private static final int LARGE_SAMPLE_SIZE = 40_000_000;
    private static final int SMALL_SAMPLE_SIZE = 5_000_000;
    
    /** Whether the generators are picky. */
    private final boolean picky;
    
    /**
     * Constructor.
     * 
     * @param picky whether the generators are picky
     */
    DuelingGenerators(boolean picky) {
        super();
        this.picky = picky;
    }
    
    /**
     * {@inheritDoc}
     * 
     * @return the judge's final count
     */
    @Override
    public long solve(Stream<String> lines) {
        List<Long> startValues = lines.filter(Predicate.not(String::isBlank))
            // Drop the prefix
            .map(line -> line.substring("Generator X starts with ".length()))
            .map(Long::valueOf)
            .toList();
        long startValueA = startValues.get(0).longValue();
        long startValueB = startValues.get(1).longValue();
        
        Generator generatorA = Generator.createGeneratorA(startValueA, picky);
        Generator generatorB = Generator.createGeneratorB(startValueB, picky);
        
        int sampleSize;
        if (picky) {
            sampleSize = SMALL_SAMPLE_SIZE;
        } else {
            sampleSize = LARGE_SAMPLE_SIZE;
        }
        
        long result = IntStream.range(0, sampleSize).filter(_ -> {
            long a = generatorA.nextValue();
            long b = generatorB.nextValue();
            
            short lowest16BitsA = (short)a;
            short lowest16BitsB = (short)b;
            
            return lowest16BitsA == lowest16BitsB;
        })
        .count();
        
        return result;
    }
}
