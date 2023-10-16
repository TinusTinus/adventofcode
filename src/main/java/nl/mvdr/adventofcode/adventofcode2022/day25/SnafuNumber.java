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
        var e = 0;
        while (2 < i / Math.pow(5, e)) {
            e++;
        }
        
        System.out.println(i + " - " + e);
        
        List<SnafuDigit> resultDigits = new ArrayList<>(e);
        
//        
//        var remaining = i;
//        while (remaining != 0) {
//            var d = remaining / Math.pow(5, e);
//        }
//        return new SnafuNumber(resultDigits);
        return SnafuNumber.ZERO;
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
        return IntStream.range(0, digits.size())
                .map(i -> digits.get(digits.size() - 1 - i).getValue() * (int)Math.pow(5, i))
                .sum();
    }
    
    @Override
    public String toString() {
        return digits.stream()
                .map(Object::toString)
                .collect(Collectors.joining());
    }
}
