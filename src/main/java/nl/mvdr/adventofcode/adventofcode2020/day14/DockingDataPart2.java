package nl.mvdr.adventofcode.adventofcode2020.day14;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.LongSolver;

/**
 * Solution to the day 14 puzzle of 2020's Advent of Code:
 * <a href="https://adventofcode.com/2020/day/14">Docking Data</a>.
 *
 * @author Martijn van de Rijdt
 */
public class DockingDataPart2 implements LongSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(DockingDataPart2.class);

    /**
     * {@inheritDoc}
     * 
     * @return the sum of all values left in memory after the initialisation program completes
     */
    @Override
    public long solve(Stream<String> linesStream) {
        List<String> instructions = linesStream.filter(Predicate.not(String::isEmpty))
                .collect(Collectors.toList());
        
        Map<Long, Long> memory = new HashMap<>();
        long onesMask = 0L;
        
        for (String instruction : instructions) {
            String[] sides = instruction.split(" = ");
            if ("mask".equals(sides[0])) {
                String bitmask = sides[1];
                onesMask = Long.parseLong(bitmask.replaceAll("X", "0"), 2);
            } else {
                String indexString = sides[0].substring(4, sides[0].length() - 1);
                long index = Long.parseLong(indexString);
                
                // Determine the actual indexes using bitmask info.
                // Zeroes have no effect.
                // Apply the ones.
                Set<Long> indexes = Set.of(Long.valueOf(index | onesMask));
                
                // TODO Apply the Xs.
                
                
                long value = Long.parseLong(sides[1]);
                
                indexes.forEach(i -> memory.put(i, Long.valueOf(value)));
            }
        }
        
        return memory.values()
                .stream()
                .mapToLong(Long::valueOf)
                .sum();
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        DockingDataPart2 instance = new DockingDataPart2();

        String result = instance.solve("input-day14-2020.txt");

        LOGGER.info(result);
    }
}
 