package nl.mvdr.adventofcode.adventofcode2020.day18;

/**
 * Representation of a number expression.
 *
 * @author Martijn van de Rijdt
 */
record Numeral(int value) implements Expression {
    
    /**
     * Parses the character representation of a numeral.
     * 
     * @param representation character representation of a (single-digit) numeral value
     * @return numeral
     */
    static Numeral of(char representation) {
        return new Numeral(Integer.parseInt("" + representation));
    }
    
    @Override
    public int evaluate() {
        return value;
    }
}
