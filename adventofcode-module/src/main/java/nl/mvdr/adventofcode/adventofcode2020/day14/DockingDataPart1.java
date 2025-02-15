package nl.mvdr.adventofcode.adventofcode2020.day14;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.solver.LongSolver;

/**
 * Solution to the day 14 puzzle of 2020's Advent of Code:
 * <a href="https://adventofcode.com/2020/day/14">Docking Data</a>.
 *
 * @author Martijn van de Rijdt
 */
public class DockingDataPart1 implements LongSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(DockingDataPart1.class);

    /**
     * {@inheritDoc}
     * 
     * @return the sum of all values left in memory after the initialisation program completes
     */
    @Override
    public long solve(Stream<String> linesStream) {
        List<String> instructions = linesStream.filter(Predicate.not(String::isEmpty))
                .collect(Collectors.toList());
        long[] memory = new long[99_999]; // indexes go up to five digits in the puzzle input
        long onesMask = 0L;
        long zeroesMask = 1L;
        
        for (String instruction : instructions) {
            
            String[] sides = instruction.split(" = ");
            if ("mask".equals(sides[0])) {
                String bitmask = sides[1];
                onesMask = Long.parseLong(bitmask.replaceAll("X", "0"), 2);
                zeroesMask = Long.parseLong(bitmask.replaceAll("X", "1"), 2);
            } else {
                String indexString = sides[0].substring(4, sides[0].length() - 1);
                int index = Integer.parseInt(indexString);
                
                long value = Long.parseLong(sides[1]);
                
                memory[index] = (value | onesMask) & zeroesMask;
            }
        }
        
        return LongStream.of(memory).sum();
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        DockingDataPart1 instance = new DockingDataPart1();

        String result = instance.solve("input-day14-2020.txt");

        LOGGER.info(result);
    }
}
 