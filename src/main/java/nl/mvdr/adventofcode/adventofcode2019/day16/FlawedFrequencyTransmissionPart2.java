package nl.mvdr.adventofcode.adventofcode2019.day16;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.LinesSolver;

/**
 * Solution to the day 16 puzzle of 2019's Advent of Code:
 * <a href="https://adventofcode.com/2019/day/16">Flawed Frequency Transmission</a>.
 *
 * @author Martijn van de Rijdt
 */
public class FlawedFrequencyTransmissionPart2 implements LinesSolver<String> {

    private static final List<Integer> BASE_PATTERN = List.of(
            Integer.valueOf(0),
            Integer.valueOf(1),
            Integer.valueOf(0),
            Integer.valueOf(-1));
    
    private static final Logger LOGGER = LoggerFactory.getLogger(FlawedFrequencyTransmissionPart2.class);

    private final int phases;

    /** Constructor. */
    public FlawedFrequencyTransmissionPart2() {
        this(100);
    }
    
    /**
     * Constructor.
     * 
     * @param phases number of phases of computation to perform
     */
    FlawedFrequencyTransmissionPart2(int phases) {
        super();
        this.phases = phases;
    }
    
    /**
     * {@inheritDoc}
     * 
     * @return eight-digit message in the final output list
     */
    @Override
    public String solve(Stream<String> lines) {
        String input = lines.findFirst().orElseThrow();
        
        List<Integer> inputDigits = input.chars()
                .map(c -> Integer.parseInt("" + (char)c))
                .boxed()
                .collect(Collectors.toList());
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i != 10_000; i++) {
            list.addAll(inputDigits);
        }
        
        for (int i = 0; i != phases; i++) {
            list = performPhase(list);
        }

        long offset = Long.parseLong(input.substring(0, 7));
        
        return list.stream()
                .skip(offset)
                .limit(8L)
                .map(Object::toString)
                .collect(Collectors.joining());
    }
    
    /**
     * Performs a single phase of the computation.
     * 
     * @param input input list
     * @return output list of the same length
     */
    private static List<Integer> performPhase(List<Integer> input) {
        return IntStream.range(0, input.size())
                .parallel()
                .mapToObj(i -> getPattern(input.size(), i))
                .mapToInt(pattern -> applyPattern(input, pattern))
                .boxed()
                .collect(Collectors.toList());
    }

    /**
     * Gets an actual pattern to apply.
     * 
     * @param length length of the pattern
     * @param position position which is being computed
     * @return pattern
     */
    private static List<Integer> getPattern(int length, int position) {
        List<Integer> unrepeatedPattern = BASE_PATTERN.stream()
                .flatMap(i -> Collections.nCopies(position + 1, i).stream())
                .collect(Collectors.toList());
        
        List<Integer> result = new ArrayList<>();
        for (int i = 1; i <= length; i++) {
            result.add(unrepeatedPattern.get(i % unrepeatedPattern.size()));
        }
        
        return result;
    }
    
    /**
     * Applies the given pattern to the given list of numbers.
     * 
     * @param input list of numbers
     * @param pattern pattern to apply
     * @return result value
     */
    private static int applyPattern(List<Integer> input, List<Integer> pattern) {
        int sum = IntStream.range(0, input.size())
                .map(i -> pattern.get(i).intValue() * input.get(i).intValue())
                .sum();
        
        int result = Math.abs(sum) % 10;
        
        LOGGER.debug("Applying pattern {} to input {}: {}", pattern, input, Integer.valueOf(result));
        
        return result;
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        FlawedFrequencyTransmissionPart2 instance = new FlawedFrequencyTransmissionPart2();

        String result = instance.solve("input-day16-2019.txt");

        LOGGER.info(result);
    }
}
 