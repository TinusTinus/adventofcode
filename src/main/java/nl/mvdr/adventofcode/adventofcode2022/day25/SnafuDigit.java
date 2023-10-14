package nl.mvdr.adventofcode.adventofcode2022.day25;

import java.util.stream.Stream;

/**
 * A digit in Special Numeral-Analogue Fuel Units.
 *
 * @author Martijn van de Rijdt
 */
enum SnafuDigit {
    
    DOUBLE_MINUS('='),
    MINUS('-'),
    ZERO('0'),
    ONE('1'),
    TWO('2');
    
    private final char representation;
    
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
     */
    SnafuDigit(char representation) {
        this.representation = representation;
    }
    
    @Override
    public String toString() {
        return "" + representation;
    }
}
