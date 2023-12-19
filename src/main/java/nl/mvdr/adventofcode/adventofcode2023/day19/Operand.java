package nl.mvdr.adventofcode.adventofcode2023.day19;

import java.util.stream.Stream;

/**
 * An operand for use in a rule, when comparing a property value.
 *
 * @author Martijn van de Rijdt
 */
enum Operand {
    LESS_THAN("<") {
        @Override
        boolean apply(int lhs, int rhs) {
            return lhs < rhs;
        }
    },
    GREATER_THAN(">") {
        @Override
        boolean apply(int lhs, int rhs) {
            return lhs > rhs;
        }
    };
    
    private final String representation;
    
    /**
     * Parses an operand.
     * 
     * @param representation string representation of the operand
     * @return operand
     */
    static Operand parse(String representation) {
        return Stream.of(values())
                .filter(value -> value.representation.equals(representation))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Not a valid operand: " + representation));
    }
    
    /**
     * Constructor.
     * @param represntation string representation of the operand
     */
    Operand(String representation) {
        this.representation = representation;
    }
    
    /**
     * Applies this operand.
     * 
     * @param lhs left-hand side
     * @param rhs right-hand side
     * @return result
     */
    abstract boolean apply(int lhs, int rhs);
}
