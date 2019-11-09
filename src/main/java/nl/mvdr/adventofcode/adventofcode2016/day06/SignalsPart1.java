package nl.mvdr.adventofcode.adventofcode2016.day06;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.PathSolver;

/**
 * Solution to the day 6 puzzle of 2016's Advent of Code:
 * <a href="https://adventofcode.com/2016/day/6">Signals and Noise</a>.
 *
 * @author Martijn van de Rijdt
 */
public class SignalsPart1 implements PathSolver<String> {

    private static final Logger LOGGER = LoggerFactory.getLogger(SignalsPart1.class);
    
    /**
     * {@inheritDoc}
     * 
     * @return error-corrected message
     */
    @Override
    public String solve(Path inputFilePath) throws IOException {
        List<String> corruptedMessages = Files.lines(inputFilePath)
                .filter(Predicate.not(String::isEmpty))
                .collect(Collectors.toList());
        
        // TODO
        return null;
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        SignalsPart1 instance = new SignalsPart1();

        String result = instance.solve("input-day06-2016.txt");

        LOGGER.info(result);
    }
}
