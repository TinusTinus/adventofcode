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
     * @return the expression represented by the given string
     */
    static Expression parse(String representation) {
        String compactRepresentation = representation.replaceAll(" ", "");
        ExpressionAndIndex parsed = parse(compactRepresentation, 0);
        if (parsed.endIndex() != compactRepresentation.length()) {
            throw new IllegalArgumentException("Unable to parse: " + representation + ", end index = " + parsed.endIndex());
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
    private static ExpressionAndIndex parse(String representation, int startIndex) {
        int index = startIndex;
        Optional<Expression> expression = Optional.empty();
        Optional<Operator> operator = Optional.empty();
        boolean endOfSubexpression = false;
        
        while (!endOfSubexpression && index < representation.length()) {
            char c = representation.charAt(index);
            if (c == ')') {
                // End of a subexpression between brackets
                index++;
                endOfSubexpression = true;
            }
            else if (Operator.isOperator(c)) {
                expression.orElseThrow(() -> new IllegalArgumentException("Unable to parse: " + representation + ", encountered an operator without a left-hand side"));
                operator.ifPresent(o -> { throw new IllegalArgumentException("Unable to parse: " + representation + ", encountered two operators in a row"); });
                
                operator = Operator.of(c);
                index++;
            } else {
                // Start of a new subexpression
                Expression subexpression;
                if (c == '(') {
                    ExpressionAndIndex expressionAndIndex = parse(representation, index + 1);
                    subexpression = expressionAndIndex.expression();
                    index = expressionAndIndex.endIndex();
                } else {
                    subexpression = Numeral.of(c);
                    index++;
                }
                
                if (operator.isPresent()) {
                    expression = Optional.of(new Operation(expression.orElseThrow(), operator.orElseThrow(), subexpression));
                    operator = Optional.empty();
                } else if (expression.isPresent()){
                    throw new IllegalArgumentException("Unable to parse: " + representation + ", encountered two subexpressions without an operator between them");
                } else {
                    expression = Optional.of(subexpression);
                }
            }
        }

        return new ExpressionAndIndex(expression.orElseThrow(), index);
    }
    
    /** @return the value of this expression */
    int evaluate();
}
