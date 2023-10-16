package nl.mvdr.adventofcode.adventofcode2022.day25;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

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
    static SnafuNumber valueOf(BigInteger i) {
        List<SnafuDigit> resultDigits = new LinkedList<>();
        var remaining = i;
        while (!remaining.equals(BigInteger.ZERO)) {
            var divisionAndRemainder = remaining.add(BigInteger.TWO).divideAndRemainder(BigInteger.valueOf(5));
            var remainder = divisionAndRemainder[1].intValueExact();
            resultDigits.add(0, SnafuDigit.values()[remainder]);
            remaining = divisionAndRemainder[0];
        }
        if (resultDigits.isEmpty()) {
            resultDigits = List.of(SnafuDigit.ZERO);
        }
        return new SnafuNumber(resultDigits);
    }
    
    /**
     * Convenience constructor for a single-digit number.
     * 
     * @param digit digit
     */
    SnafuNumber(SnafuDigit digit) {
        this(List.of(digit));
    }
    
    /**
     * Convenience constructor for a double-digit number.
     */
    SnafuNumber(SnafuDigit firstDigit, SnafuDigit secondDigit) {
        this(List.of(firstDigit, secondDigit));
    }
    
    /**
     * Adds the given other SNAFU number to this one.
     * 
     * @param other other number
     * @return sum of the given numbers
     */
    SnafuNumber add(SnafuNumber other) {
        var sum = this.bigIntegerValue().add(other.bigIntegerValue());
        return valueOf(sum);
    }
    
    /**
     * @return integer value corresponding to this number
     */
    BigInteger bigIntegerValue() {
        var result = BigInteger.ZERO;
        var place = BigInteger.ONE;
        for (var digit : digits.reversed()) {
            result = result.add(digit.bigIntegerValue().multiply(place));
            place = place.multiply(BigInteger.valueOf(5));
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
