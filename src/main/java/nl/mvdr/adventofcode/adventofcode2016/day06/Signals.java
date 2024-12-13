package nl.mvdr.adventofcode.adventofcode2016.day06;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import nl.mvdr.adventofcode.solver.LinesSolver;

/**
 * Solution to the day 6 puzzle of 2016's Advent of Code:
 * <a href="https://adventofcode.com/2016/day/6">Signals and Noise</a>.
 *
 * @author Martijn van de Rijdt
 */
abstract class Signals implements LinesSolver<String> {

    private final boolean min;
    
    /**
     * Constructor.
     * 
     * @param min whether the minimum number of occurrences determines the character; if false, the maximum is used instead
     */
    Signals(boolean min) {
        super();
        this.min = min;
    }
    
    /**
     * {@inheritDoc}
     * 
     * @return error-corrected message
     */
    @Override
    public String solve(Stream<String> lines) {
        List<String> corruptedMessages = lines.filter(Predicate.not(String::isEmpty))
                .collect(Collectors.toList());
        
        return IntStream.range(0, corruptedMessages.getFirst().length())
                .mapToObj(index -> findCharacterAt(index, corruptedMessages))
                .map(Object::toString)
                .collect(Collectors.joining());
    }
    
    /**
     * Finds the correct character at the given index in the message.
     * 
     * @param index index in the message string
     * @param corruptedMessages messages as received (all of the same length)
     * @return the character at the given index
     */
    private Character findCharacterAt(int index, List<String> corruptedMessages) {
        Map<Character, Long> counters = corruptedMessages.stream()
                .map(message -> Character.valueOf(message.charAt(index)))
                // count occurrences of each letter
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        Comparator<Entry<Character, Long>> comparator = Entry.comparingByValue();
        if (min) {
            comparator = comparator.reversed();
        }

        return counters.entrySet().stream()
                .max(comparator)
                .map(Entry::getKey)
                .orElseThrow();
    }
}
