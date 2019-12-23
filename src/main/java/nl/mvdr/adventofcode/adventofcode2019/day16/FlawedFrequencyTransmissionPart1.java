package nl.mvdr.adventofcode.adventofcode2019.day16;

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
public class FlawedFrequencyTransmissionPart1 implements LinesSolver<String> {

    private static final List<Integer> BASE_PATTERN = List.of(
            Integer.valueOf(0),
            Integer.valueOf(1),
            Integer.valueOf(0),
            Integer.valueOf(-1));
    
    private static final Logger LOGGER = LoggerFactory.getLogger(FlawedFrequencyTransmissionPart1.class);

    private final int phases;

    /** Constructor. */
    public FlawedFrequencyTransmissionPart1() {
        this(100);
    }
    
    /**
     * Constructor.
     * 
     * @param phases number of phases of computation to perform
     */
    FlawedFrequencyTransmissionPart1(int phases) {
        super();
        this.phases = phases;
    }
    
    /**
     * {@inheritDoc}
     * 
     * @return first eight digits in the final output list
     */
    @Override
    public String solve(Stream<String> lines) {
        String input = lines.findFirst().orElseThrow();
        
        List<Integer> list = input.chars()
                .map(c -> Integer.parseInt("" + (char)c))
                .boxed()
                .collect(Collectors.toList());
        
        for (int i = 0; i != phases; i++) {
            list = performPhase(list);
            
            LOGGER.debug("After phase {}: {}", Integer.valueOf(i), list);
        }
        
        return list.stream()
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
                .map(i -> applyPattern(input, i))
                .boxed()
                .collect(Collectors.toList());
    }

    /**
     * Applies the given pattern to the given list of numbers.
     * 
     * @param input list of numbers
     * @param position the position whose pattern to apply
     * @return result value
     */
    private static int applyPattern(List<Integer> input, int position) {
        int sum = IntStream.range(0, input.size())
                .map(i -> getPatternValue(position, i) * input.get(i).intValue())
                .sum();
        
        return Math.abs(sum) % 10;
    }
    
    /**
     * Gets a pattern value.
     * 
     * @param position position identifying the pattern to apply
     * @param index index within the pattern
     * @return pattern value
     */
    private static int getPatternValue(int position, int index) {
        int basePatternIndex;
        if (index < position) {
            basePatternIndex = 0;
        } else {
            basePatternIndex = ((index - position) / (position + 1) + 1) % BASE_PATTERN.size();
        }
        return BASE_PATTERN.get(basePatternIndex).intValue();
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        FlawedFrequencyTransmissionPart1 instance = new FlawedFrequencyTransmissionPart1();

        String result = instance.solve("input-day16-2019.txt");

        LOGGER.info(result);
    }
}
 