package nl.mvdr.adventofcode.adventofcode2017.day06;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
public class MemoryReallocationPart1 implements PathSolver<Integer> {

    private static final Logger LOGGER = LoggerFactory.getLogger(MemoryReallocationPart1.class);
    
    /**
     * {@inheritDoc}
     * 
     * @return how many redistribution cycles must be completed before a configuration is produced that has been seen before
     */
    @Override
    public Integer solve(Path inputFilePath) throws IOException {
        List<Integer> banks = parseInput(inputFilePath);
        
        LOGGER.debug("Starting banks: {}", banks);
        
        Set<List<Integer>> history = new HashSet<>();
        
        while (history.add(banks)) {
            banks = new ArrayList<>(banks);
            int index = IntStream.range(0, banks.size())
                    .boxed()
                    .max(Comparator.comparing(banks::get))
                    .get()
                    .intValue();
            
            int remainingBanks = banks.get(index);
            banks.set(index, Integer.valueOf(0));
            
            while (0 < remainingBanks) {
                index = (index + 1) % banks.size();
                banks.set(index, Integer.valueOf(banks.get(index).intValue() + 1));
                remainingBanks--;
            }
        }
        
        return Integer.valueOf(history.size());
    }

    private List<Integer> parseInput(Path inputFilePath) throws IOException {
        String line = Files.lines(inputFilePath)
                .findFirst()
                .get();
        
        return Stream.of(line.split("\\s"))
                .map(Integer::valueOf)
                .collect(Collectors.toList());
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        MemoryReallocationPart1 instance = new MemoryReallocationPart1();

        String result = instance.solve("input-day06-2017.txt");

        LOGGER.info(result);
    }
}
