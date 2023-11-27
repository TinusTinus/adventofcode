package nl.mvdr.adventofcode.adventofcode2021.day03;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.IntSolver;

/**
 * Solution to <a href="https://adventofcode.com/2021/day/3">Binary Diagnostic</a>.
 *
 * @author Martijn van de Rijdt
 */
public class BinaryDiagnosticPart1 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(BinaryDiagnosticPart1.class);

    @Override
    public int solve(Stream<String> lines) {
        var numbers = lines.toList();
        
        var gammaRateString = calculateGammaRate(numbers);
        var gammaRate = Integer.parseInt(gammaRateString, 2);
        LOGGER.debug("Gamma rate: {}, decimal: {}", gammaRateString, Integer.valueOf(gammaRate));
        
        var epsilonRateString = invert(gammaRateString);
        int epsilonRate = Integer.parseInt(epsilonRateString, 2);
        LOGGER.debug("Epsilon rate: {}, decimal: {}", epsilonRateString, Integer.valueOf(epsilonRate));
        
        return gammaRate * epsilonRate;
    }
    
    /**
     * 
     * @param numbers diagnostic report 
     * @return gamma rate
     */
    private static String calculateGammaRate(List<String> numbers) {
        return IntStream.range(0, numbers.get(0).length())
                .mapToObj(i -> Character.valueOf(mostCommonCharacterAt(numbers, i)))
                .map(Object::toString)
                .collect(Collectors.joining());
    }
    
    /**
     * Finds the most common character at the given position in the given list of strings.
     * 
     * @param numbers list of string reprsentations of binary numbers
     * @param position position from the beginning of the string
     * @return most common chracter at the given position (either '0' or '1')
     */
    private static char mostCommonCharacterAt(List<String> numbers, int position) {
        var zeroCount = Math.toIntExact(numbers.stream()
                .filter(number -> number.charAt(position) == '0')
                .count());
        var oneCount = numbers.size() - zeroCount;
        char result;
        if (zeroCount < oneCount) {
            result = '1';
        } else if (oneCount < zeroCount) {
            result = '0';
        } else {
            throw new IllegalArgumentException("Unable to determine most common character at position " + position
                    + ", given the numbers: " + numbers);
        }
        return result;
    }
    
    /**
     * Inverts each bit of the given string representation of a binary number.
     * 
     * @param number string representation of a binary number, such as '01010'
     * @return inversion, in this example: '10101'
     */
    private static String invert(String number) {
        return number.chars()
            .map(i -> switch(i) {
                case '0' -> '1';
                case '1' -> '0';
                default -> throw new IllegalArgumentException("Unexpected input: " + (char) i);
            })
            .mapToObj(i -> "" + (char) i)
            .collect(Collectors.joining());
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        var instance = new BinaryDiagnosticPart1();

        var result = instance.solve("input-day03-2021.txt");

        LOGGER.info(result);
    }
}
 