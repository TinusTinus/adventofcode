package nl.mvdr.adventofcode.adventofcode2021.day03;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Helper class containing useful utility methods.
 *
 * @author Martijn van de Rijdt
 */
class BinaryDiagnosticHelper {
    /**
     * Finds the most common character at the given position in the given list of strings.
     * 
     * @param numbers list of string representations of binary numbers
     * @param position position from the beginning of the string
     * @return most common chracter at the given position (either '0' or '1'); '1' if equally common
     */
    static char mostCommonCharacterAt(List<String> numbers, int position) {
        var zeroCount = Math.toIntExact(numbers.stream()
                .filter(number -> number.charAt(position) == '0')
                .count());
        var oneCount = numbers.size() - zeroCount;
        char result;
        if (oneCount < zeroCount) {
            result = '0';
        } else {
            result = '1';
        }
        return result;
    }
    
    /**
     * Finds the least common character at the given position in the given list of strings.
     * 
     * @param numbers list of string representations of binary numbers
     * @param position position from the beginning of the string
     * @return least common chracter at the given position (either '0' or '1'); '0' if equally common
     */
    static char leastCommonCharacterAt(List<String> numbers, int position) {
        var mostCommon = mostCommonCharacterAt(numbers, position);
        return invert(mostCommon);
    }

    
    /**
     * Inverts each bit of the given string representation of a binary number.
     * 
     * @param number string representation of a binary number, such as '01010'
     * @return inversion, in this example: '10101'
     */
    static String invert(String number) {
        return number.chars()
            .map(bit -> invert((char) bit))
            .mapToObj(bit -> "" + (char) bit)
            .collect(Collectors.joining());
    }
    
    /**
     * Inverts the given bit (as represented by a character).
     * 
     * @param bit character representation of a bit: '0' or '1'
     * @return inverted value
     */
    private static char invert(char bit) {
        return switch(bit) {
            case '0' -> '1';
            case '1' -> '0';
            default -> throw new IllegalArgumentException("Unexpected input: " + bit);
        };
    }
}
