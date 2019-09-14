package nl.mvdr.adventofcode.adventofcode2017.day17;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.PathSolver;

/**
 * Solution to the day 17 puzzle of 2017's Advent of Code:
 * <a href="https://adventofcode.com/2017/day/17">Spinlock</a>.
 *
 * @author Martijn van de Rijdt
 */
abstract class Spinlock implements PathSolver<Integer> {

    private static final Logger LOGGER = LoggerFactory.getLogger(Spinlock.class);
    
    private final int insertions;
    
    /**
     * Constructor.
     * 
     * @param insertions the number of insertions to perform into the buffer
     */
    Spinlock(int insertions) {
        super();
        this.insertions = insertions;
    }
    
    @Override
    public Integer solve(Path inputFilePath) throws IOException {
        int stepSize = Files.lines(inputFilePath)
                .mapToInt(Integer::parseInt)
                .findFirst()
                .getAsInt();
        
        LinkedList<Integer> buffer = new LinkedList<>();
        buffer.add(Integer.valueOf(0));

        // Current position is always at index 0.
        
        for (int i = 1; i != insertions + 1; i++) {
            IntStream.range(0, stepSize + 1)
                    .forEach(j -> buffer.offerLast(buffer.pollFirst()));
            
            buffer.offerFirst(Integer.valueOf(i));
            
            log(buffer);
        }
        
        return solve(buffer);
    }
    
    private void log(List<Integer> buffer) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace(IntStream.range(0, buffer.size())
                    .mapToObj(j -> {
                        String result;
                        if (j == 0) {
                            result = "(" + buffer.get(j) + ")";
                        } else {
                            result = buffer.get(j).toString();
                        }
                        return result;
                    })
                    .collect(Collectors.joining(" ")));
        }
        
        if (LOGGER.isDebugEnabled() && buffer.size() % 10_000 == 0) {
            LOGGER.debug("Buffer size: " + Integer.valueOf(buffer.size()));
            LOGGER.debug("If this had been the final insertion, the solution would be: " + solve(buffer));
        }
    }
    
    /**
     * Solver method.
     * 
     * @param buffer buffer after performing every insertion; 0 is the current position
     * @return solution to the puzzle
     */
    abstract Integer solve(List<Integer> buffer);
}
