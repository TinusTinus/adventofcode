package nl.mvdr.adventofcode.adventofcode2022.day25;

import java.math.BigInteger;
import java.util.stream.Stream;

/**
 * A digit in Special Numeral-Analogue Fuel Units.
 *
 * @author Martijn van de Rijdt
 */
enum SnafuDigit {
    
    DOUBLE_MINUS('=', -2),
    MINUS('-', -1),
    ZERO('0', 0),
    ONE('1', 1),
    TWO('2', 2);
    
    private final char representation;
    private final int value;
    
    /**
     * Finds the digit represented by the given character.
     * 
     * @param representation character representation of the SNAFU digit
     * @return SNAFU digit
     */
    static SnafuDigit of(char representation) {
        return Stream.of(values())
                .filter(digit -> digit.representation == representation)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Not a SNAFU digit: " + representation));
    }
    
    /**
     * Constructor.
     * 
     * @param representation single-character representation of this digit
     * @param value the integer value of this digit
     */
    SnafuDigit(char representation, int value) {
        this.representation = representation;
        this.value = value;
    }
    
    /**
     * @return the integer value of this digit
     */
    BigInteger bigIntegerValue() {
        return BigInteger.valueOf(value);
    }
    
    @Override
    public String toString() {
        return "" + representation;
    }
}
