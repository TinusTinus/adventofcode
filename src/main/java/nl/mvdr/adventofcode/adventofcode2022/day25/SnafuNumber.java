package nl.mvdr.adventofcode.adventofcode2022.day25;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * A number in Special Numeral-Analogue Fuel Units.
 *
 * @author Martijn van de Rijdt
 */
record SnafuNumber(List<SnafuDigit> digits) {
    
    static final SnafuNumber ZERO = new SnafuNumber(List.of(SnafuDigit.ZERO));
    
    /**
     * Parses a textual representation of a SNAFU number.
     * 
     * @param text text representation of a SNAFU number, such as "1=-0-2"
     * @return the number
     */
    static SnafuNumber parse(String text) {
        var digits = text.chars()
                .mapToObj(c -> SnafuDigit.of((char)c))
                .toList();
        return new SnafuNumber(digits);
    }
    
    /**
     * Converts an integer to a SNAFU number.
     * 
     * @param i integer value
     * @return SNAFU number
     */
    static SnafuNumber valueOf(int i) {
        List<SnafuDigit> resultDigits = new ArrayList<>();
        // TODO stuff
        return new SnafuNumber(resultDigits);
    }
    
    /**
     * Adds the given other SNAFU number to this one.
     * 
     * @param other other number
     * @return sum of the given numbers
     */
    SnafuNumber add(SnafuNumber other) {
        var sum = this.intValue() + other.intValue();
        return valueOf(sum);
    }
    
    /**
     * @return integer value corresponding to this number
     */
    int intValue() {
        var result = 0;
        var place = 1;
        for (var digit : digits.reversed()) {
            result = result + digit.getValue() * place;
            place = place * 5;
        }
        return result;
    }
    
    @Override
    public String toString() {
        return digits.stream()
                .map(Object::toString)
                .collect(Collectors.joining());
    }
}
