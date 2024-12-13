package nl.mvdr.adventofcode.adventofcode2017.day06;

import nl.mvdr.adventofcode.solver.IntSolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Solution to the day 6 puzzle of 2017's Advent of Code:
 * <a href="https://adventofcode.com/2017/day/6">Memory Reallocation</a>.
 *
 * @author Martijn van de Rijdt
 */
abstract class MemoryReallocation implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(MemoryReallocation.class);
    
    @Override
    public int solve(Stream<String> lines) {
        List<Integer> banks = parseInput(lines.findFirst().orElseThrow());
        
        LOGGER.debug("Starting banks: {}", banks);
        
        // Keys: blocks-in-banks configurations, values: the number of cycles after which each of these occurred.
        Map<List<Integer>, Integer> history = new HashMap<>();
        
        while (!history.containsKey(banks)) {
            history.put(banks, Integer.valueOf(history.size()));
            banks = new ArrayList<>(banks);
            int index = IntStream.range(0, banks.size())
                    .boxed()
                    .max(Comparator.comparing(banks::get))
                    .orElseThrow()
                    .intValue();
            
            int remainingBanks = banks.get(index).intValue();
            banks.set(index, Integer.valueOf(0));
            
            while (0 < remainingBanks) {
                index = (index + 1) % banks.size();
                banks.set(index, Integer.valueOf(banks.get(index).intValue() + 1));
                remainingBanks--;
            }
        }
        
        return solve(banks, history);
    }

    /**
     * Parses the input into the initial memory banks.
     * 
     * @param line puzzle input
     * @return memory banks
     */
    private List<Integer> parseInput(String line) {
        return Stream.of(line.split("\\s"))
                .map(Integer::valueOf)
                .collect(Collectors.toList());
    }
    
    /**
     * Returns the solution.
     * 
     * @param banks final memory banks value, after a cycle has been detected
     * @param history history
     * @return puzzle solution
     */
    protected abstract int solve(List<Integer> banks, Map<List<Integer>, Integer> history);
}
