package nl.mvdr.adventofcode.adventofcode2020.day18;

/**
 * Representation of an expression.
 *
 * @author Martijn van de Rijdt
 */
interface Expression {
    
    /**
     * Parses the string representation of an expression.
     * 
     * @param representation string representation of an expression
     * @return the expression represented by the given string
     */
    static Expression parse(String representation) {
        return null; // TODO implement
    }
    
    /** @return the value of this expression */
    int evaluate();
}
