package nl.mvdr.adventofcode.adventofcode2016.day06;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.PathSolver;

/**
 * Solution to the day 6 puzzle of 2016's Advent of Code:
 * <a href="https://adventofcode.com/2016/day/6">Signals and Noise</a>.
 *
 * @author Martijn van de Rijdt
 */
public class SignalsPart2 implements PathSolver<String> {

    private static final Logger LOGGER = LoggerFactory.getLogger(SignalsPart2.class);
    
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
        
        return IntStream.range(0, corruptedMessages.get(0).length())
                .mapToObj(index -> findCharacterAt(index, corruptedMessages))
                .map(Object::toString)
                .collect(Collectors.joining());
    }
    
    /**
     * Finds the correct character at the given index in the message.
     * 
     * @param index index in the message string
     * @param corruptedMessages messages as received (all of the same length)
     * @return the character which occurs most frequently at the given index
     */
    private Character findCharacterAt(int index, List<String> corruptedMessages) {
        Map<Character, Long> counters = corruptedMessages.stream()
                .map(message -> Character.valueOf(message.charAt(index)))
                // count occurrences of each letter
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        
        return counters.entrySet().stream()
                .min(Comparator.comparing(Entry::getValue))
                .map(Entry::getKey)
                .get();
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        SignalsPart2 instance = new SignalsPart2();

        String result = instance.solve("input-day06-2016.txt");

        LOGGER.info(result);
    }
}
