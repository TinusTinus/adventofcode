package nl.mvdr.adventofcode.adventofcode2022.day25;

import java.math.BigInteger;
import java.util.stream.Stream;

/**
 * A digit in Special Numeral-Analogue Fuel Units.
 *
 * @author Martijn van de Rijdt
 */
enum SnafuDigit {
    
    DOUBLE_MINUS('=', -2) {
        @Override
        SnafuNumber add(SnafuDigit other) {
            return switch(other) {
                case DOUBLE_MINUS   -> new SnafuNumber(MINUS, ONE);
                case MINUS          -> new SnafuNumber(MINUS, TWO);
                case ZERO, ONE, TWO -> other.add(this);
            };
        }
    },
    MINUS('-', -1) {
        @Override
        SnafuNumber add(SnafuDigit other) {
                return switch(other) {
                    case DOUBLE_MINUS          -> new SnafuNumber(DOUBLE_MINUS);
                    case MINUS, ZERO, ONE, TWO -> other.add(this);
            };
        }
    },
    ZERO('0', 0) {
        @Override
        SnafuNumber add(SnafuDigit other) {
            return new SnafuNumber(other);
        }
    },
    ONE('1', 1) {
        @Override
        SnafuNumber add(SnafuDigit other) {
            return switch(other) {
                case DOUBLE_MINUS  -> new SnafuNumber(MINUS);
                case MINUS         -> new SnafuNumber(ZERO);
                case ONE           -> new SnafuNumber(TWO);
                case TWO           -> new SnafuNumber(ONE, DOUBLE_MINUS);
                case ZERO          -> other.add(this);
            };
        }
    },
    TWO('2', 2) {
        @Override
        SnafuNumber add(SnafuDigit other) {
            return switch(other) {
                case DOUBLE_MINUS -> new SnafuNumber(ZERO);
                case MINUS        -> new SnafuNumber(ONE);
                case TWO          -> new SnafuNumber(ONE, MINUS);
                case ZERO, ONE    -> other.add(this);
            };
        }
    };
    
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
    
    /**
     * Adds tbe given other digit to this one.
     * 
     * @param other other digit value
     * @return sum of the two single-digit numbers
     */
    abstract SnafuNumber add(SnafuDigit other);
}
