package nl.mvdr.adventofcode.adventofcode2020.day18;

import java.util.Optional;

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
     * @param advanced whether to apply "advanced math" rules (part 2 of the puzzle)
     * @return the expression represented by the given string
     */
    static Expression parse(String representation, boolean advanced) {
        String compactRepresentation = representation.replaceAll(" ", "");
        ExpressionAndIndex parsed = parse(compactRepresentation, 0, advanced, true);
        return parsed.expression();
    }
    
    /**
     * Parses (part of) the string representation of an expression.
     * 
     * @param representation string representation of an expression, stripped of all whitespace
     * @param startIndex start index in the given string representation
     * @param advanced whether to apply "advanced math" rules (part 2 of the puzzle)
     * @param includeClosingBracket whether to include the encountered closing bracket in the end index value
     * @return the subexpression starting at the given {@code startIndex}, as well as the end index of the subexpression
     */
    private static ExpressionAndIndex parse(String representation, int startIndex, boolean advanced, boolean includeClosingBracket) {
        int index = startIndex;
        Optional<Expression> expression = Optional.empty();
        Optional<Operator> operator = Optional.empty();
        boolean endOfSubexpression = false;
        
        while (!endOfSubexpression && index < representation.length()) {
            char c = representation.charAt(index);
            if (c == ')') {
                // End of a subexpression between brackets
                if (includeClosingBracket) {
                    index++;
                }
                endOfSubexpression = true;
            }
            else if (Operator.isOperator(c)) {
                operator = Operator.of(c);
                
                if (advanced && operator.orElseThrow() == Operator.MULTIPLICATION) {
                    // In advanced math, addition takes precedence over multiplication.
                    // Parse the right-hand side first, in case it includes addition.
                    ExpressionAndIndex expressionAndIndex = parse(representation, index + 1, advanced, false);
                    expression = Optional.of(new Operation(expression.orElseThrow(), operator.orElseThrow(), expressionAndIndex.expression()));
                    operator = Optional.empty();
                    index = expressionAndIndex.endIndex();
                } else {
                    index++;
                }
            } else {
                // Start of a new subexpression
                Expression subexpression;
                if (c == '(') {
                    // Parse the subexpression between the brackets.
                    ExpressionAndIndex expressionAndIndex = parse(representation, index + 1, advanced, true);
                    subexpression = expressionAndIndex.expression();
                    index = expressionAndIndex.endIndex();
                } else {
                    // Not a bracket and not an operator: this must be a numeral.
                    subexpression = Numeral.of(c);
                    index++;
                }
                
                if (operator.isPresent()) {
                    // This subexpression is the right-hand side of the operator: create an operation expression.
                    expression = Optional.of(new Operation(expression.orElseThrow(), operator.orElseThrow(), subexpression));
                    operator = Optional.empty();
                } else {
                    expression = Optional.of(subexpression);
                }
            }
        }

        return new ExpressionAndIndex(expression.orElseThrow(), index);
    }
    
    /** @return the value of this expression */
    long evaluate();
}
