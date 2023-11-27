package nl.mvdr.adventofcode.adventofcode2021.day03;

import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.IntSolver;

/**
 * Solution to <a href="https://adventofcode.com/2021/day/3">Binary Diagnostic</a>.
 *
 * @author Martijn van de Rijdt
 */
public class BinaryDiagnosticPart2 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(BinaryDiagnosticPart2.class);

    @Override
    public int solve(Stream<String> lines) {
        var numbers = lines.toList();
        
        var oxygenGeneratorRatingString = findOxygenGeneratorRating(numbers);
        var oxygenGeneratorRating = Integer.parseInt(oxygenGeneratorRatingString, 2);
        
        var co2ScrubberRatingString = findCO2ScrubberRating(numbers);
        var co2ScrubberRating = Integer.parseInt(co2ScrubberRatingString, 2);
        
        return oxygenGeneratorRating * co2ScrubberRating;
    }
    
    /**
     * Finds the oxygen generator rating.
     * 
     * @param numbers diagnostic report
     * @return oxygen generator rating
     */
    private String findOxygenGeneratorRating(List<String> numbers) {
        return findRating(numbers, 0, BinaryDiagnosticHelper::mostCommonCharacterAt);
    }
    
    /**
     * Finds the CO2 scrubber rating.
     * 
     * @param numbers diagnostic report
     * @return CO2 scrubber rating
     */
    private String findCO2ScrubberRating(List<String> numbers) {
        return findRating(numbers, 0, BinaryDiagnosticHelper::leastCommonCharacterAt);
    }
    
    /**
     * Recursive function to find a rating.
     * 
     * @param numbers remaining candidate numbers
     * @param position next position to consider
     * @param bitCriteria the bit criteria to apply for the rating
     * @return rating value
     */
    private String findRating(List<String> numbers, int position, BiFunction<List<String>, Integer, Character> bitCriteria) {
        String result;
        if (numbers.isEmpty()) {
            throw new IllegalArgumentException("No numbers remaining.");
        } else if (numbers.size() == 1) {
            result = numbers.get(0);
        } else {
            var bitSelectionValue = bitCriteria.apply(numbers, Integer.valueOf(position)).charValue();
            var remainingNumbers = numbers.stream()
                    .filter(number -> number.charAt(position) == bitSelectionValue)
                    .toList();
            result = findRating(remainingNumbers, position + 1, bitCriteria);
        }
        
        return result;
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        var instance = new BinaryDiagnosticPart2();

        var result = instance.solve("input-day03-2021.txt");

        LOGGER.info(result);
    }
}
 