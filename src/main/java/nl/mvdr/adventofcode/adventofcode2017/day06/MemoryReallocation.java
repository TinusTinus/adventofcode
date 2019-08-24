package nl.mvdr.adventofcode.adventofcode2017.day06;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.PathSolver;

/**
 * Solution to the day 6 puzzle of 2017's Advent of Code:
 * <a href="https://adventofcode.com/2017/day/6">Memory Reallocation</a>.
 *
 * @author Martijn van de Rijdt
 */
abstract class MemoryReallocation implements PathSolver<Integer> {

    private static final Logger LOGGER = LoggerFactory.getLogger(MemoryReallocation.class);
    
    @Override
    public Integer solve(Path inputFilePath) throws IOException {
        List<Integer> banks = parseInput(inputFilePath);
        
        LOGGER.debug("Starting banks: {}", banks);
        
        // Keys: blocks-in-banks configurations, values: the number of cycles after which each of these occurred.
        Map<List<Integer>, Integer> history = new HashMap<>();
        
        while (!history.containsKey(banks)) {
            history.put(banks, Integer.valueOf(history.size()));
            banks = new ArrayList<>(banks);
            int index = IntStream.range(0, banks.size())
                    .boxed()
                    .max(Comparator.comparing(banks::get))
                    .get()
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
     * @param inputFilePath puzzle input file
     * @return memory banks
     * @throws IOException unexpected I/O exception when reading the input
     */
    private List<Integer> parseInput(Path inputFilePath) throws IOException {
        String line = Files.lines(inputFilePath)
                .findFirst()
                .get();
        
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
    protected abstract Integer solve(List<Integer> banks, Map<List<Integer>, Integer> history);
}
