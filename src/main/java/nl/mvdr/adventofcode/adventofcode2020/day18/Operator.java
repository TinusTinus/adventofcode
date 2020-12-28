package nl.mvdr.adventofcode.adventofcode2020.day18;

import java.util.stream.Stream;

/**
 * Representation of an operator.
 *
 * @author Martijn van de Rijdt
 */
enum Operator {
    /** Addition, used to compute the sum of two values. */
    ADDITION('+') {
        @Override
        int apply(int lhs, int rhs) {
            return lhs + rhs;
        }
    },
    
    /** Multiplication, used to compute the product of two values. */
    MULTIPLICATION('*') {
        @Override
        int apply(int lhs, int rhs) {
            return lhs * rhs;
        }
    };
    
    private final char representation;
    
    /**
     * Constructor.
     * 
     * @param representation character representation of this operator
     */
    Operator(char representation) {
        this.representation = representation;
    }
    
    /**
     * Returns the operator represented by the given character.
     * 
     * @param c character representation
     * @return operator
     */
    static Operator of(char c) {
        return Stream.of(values())
                .filter(operator -> operator.representation == c)
                .findFirst()
                .orElseThrow();
    }
    
    /**
     * Apply the operation represented by this operator to the given values.
     * 
     * @param lhs left-hand side value
     * @param rhs right-hand side value
     * @return value after applying this operator
     */
    abstract int apply(int lhs, int rhs);
}
