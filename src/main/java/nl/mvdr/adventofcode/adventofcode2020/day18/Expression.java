package nl.mvdr.adventofcode.adventofcode2020.day18;

import java.util.Objects;

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
        String compactRepresentation = representation.replaceAll(" ", "");
        ExpressionAndIndex parsed = parse(compactRepresentation, 0, 0);
        if (parsed.endIndex() != compactRepresentation.length()) {
            throw new IllegalArgumentException("Unable to parse expression: " + representation);
        }
        return parsed.expression();
    }
    
    /**
     * Parses (part of) the string representation of an expression.
     * 
     * @param representation string representation of an expression, stripped of all whitespace
     * @param startIndex start index in the given string representation
     * @return the subexpression starting at the given {@code startIndex}, as well as the end index of the subexpression
     */
    private static ExpressionAndIndex parse(String representation, int startIndex, int depth) {
        int index = startIndex;
        Expression expression = null;
        
        while (index < representation.length() && representation.charAt(index) != ')') {
            char c = representation.charAt(index);
            if (c == '(') {
                ExpressionAndIndex subexpressionAndIndex = parse(representation, index + 1, depth + 1);
                expression = subexpressionAndIndex.expression();
                index = subexpressionAndIndex.endIndex();
            } else if (Character.isDigit(c)) {
                expression = new Numeral(c);
                index++;
            } else {
                Operator operator = Operator.of(c);
                ExpressionAndIndex subexpressionAndIndex = parse(representation, index + 1, depth);
                expression = new Operation(expression, operator, subexpressionAndIndex.expression());
                index = subexpressionAndIndex.endIndex();
            }
        }

        return new ExpressionAndIndex(Objects.requireNonNull(expression), index);
    }
    
    /** @return the value of this expression */
    int evaluate();
}
