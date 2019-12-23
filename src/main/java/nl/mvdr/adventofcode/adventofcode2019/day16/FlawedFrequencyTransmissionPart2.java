package nl.mvdr.adventofcode.adventofcode2019.day16;

import java.util.Arrays;
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
    
    private static final Logger LOGGER = LoggerFactory.getLogger(FlawedFrequencyTransmissionPart2.class);

    /**
     * {@inheritDoc}
     * 
     * @return eight-digit message in the final output list
     */
    @Override
    public String solve(Stream<String> lines) {
        String input = lines.findFirst().orElseThrow();
        
        int offset = Integer.parseInt(input.substring(0, 7));
        
        List<Integer> inputDigits = input.chars()
                .map(c -> Integer.parseInt("" + (char)c))
                .boxed()
                .collect(Collectors.toList());
        
        int[] digits = new int[inputDigits.size() * 10_000];
        for (int i = 0; i != 10_000; i++) {
            for (int j = 0; j != inputDigits.size(); j++) {
                digits[i * inputDigits.size() + j] = inputDigits.get(j).intValue();
            }
        }
        
        // Drop the digits before offset, since they do not matter to the computation
        digits = Arrays.copyOfRange(digits, offset, digits.length);
        
        for (int i = 0; i != 100; i++) {
            digits = performPhase(digits);
            
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("After phase {}: {}", Integer.valueOf(i), Arrays.toString(digits));
            }
        }
        
        return IntStream.of(digits)
                .limit(8L)
                .mapToObj(Integer::toString)
                .collect(Collectors.joining());
    }
    
    /**
     * Performs a single phase of the computation.
     * 
     * @param input input list
     * @return output list of the same length
     */
    private static int[] performPhase(int[] input) {
        int[] result = new int[input.length];
        
        result[input.length - 1] = input[input.length - 1];
        
        for (int i = input.length - 2; 0 <= i; i--) {
            result[i] = (result[i + 1] + input[i]) % 10;
        }
        
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
 