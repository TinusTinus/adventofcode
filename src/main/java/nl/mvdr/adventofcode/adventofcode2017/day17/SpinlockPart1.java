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
public class SpinlockPart1 implements PathSolver<Integer> {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpinlockPart1.class);
    
    /**
     * {@inheritDoc}
     * 
     * @return number after 2017
     */
    @Override
    public Integer solve(Path inputFilePath) throws IOException {
        int stepSize = Files.lines(inputFilePath)
                .mapToInt(Integer::parseInt)
                .findFirst()
                .getAsInt();
        
        List<Integer> buffer = new LinkedList<>();
        buffer.add(Integer.valueOf(0));
        int currentPosition = 0;
        
        for (int i = 1; i != 2018; i++) {
            currentPosition = (currentPosition + stepSize + 1) % buffer.size();
            buffer.add(currentPosition, Integer.valueOf(i));
            
            log(buffer, currentPosition);
        }
        
        return buffer.get(currentPosition + 1);
    }

    private void log(List<Integer> buffer, int currentPosition) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(IntStream.range(0, buffer.size())
                    .mapToObj(j -> {
                        String result;
                        if (j == currentPosition) {
                            result = "(" + buffer.get(j) + ")";
                        } else {
                            result = buffer.get(j).toString();
                        }
                        return result;
                    })
                    .collect(Collectors.joining(" ")));
        }
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        SpinlockPart1 instance = new SpinlockPart1();

        String result = instance.solve("input-day17-2017.txt");

        LOGGER.info(result);
    }
}
