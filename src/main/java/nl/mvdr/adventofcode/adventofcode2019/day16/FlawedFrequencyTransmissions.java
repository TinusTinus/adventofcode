package nl.mvdr.adventofcode.adventofcode2019.day16;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Utility class for computing Flawed Frequency Transmissions.
 *
 * @author Martijn van de Rijdt
 */
public class FlawedFrequencyTransmissions {

    private static final List<Integer> BASE_PATTERN = List.of(
            Integer.valueOf(0),
            Integer.valueOf(1),
            Integer.valueOf(0),
            Integer.valueOf(-1));

    private FlawedFrequencyTransmissions() {
        // private constructor to prevent utility class instantiation
    }
    
    /**
     * Decodes the given message using Flawed Frequency Transmission.
     * 
     * @param input input message
     * @param phases number of phases
     * @param repititions number of times to repeat the input
     * @param offset offset in the output
     * @return output
     */
    static String fft(String input, int phases, int repititions, long offset) {
        List<Integer> inputDigits = input.chars()
                .map(c -> Integer.parseInt("" + (char)c))
                .boxed()
                .collect(Collectors.toList());
        
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i != repititions; i++) {
            list.addAll(inputDigits);
        }
        
        for (int i = 0; i != phases; i++) {
            list = performPhase(list);
        }
        
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
}
 