package nl.mvdr.adventofcode.adventofcode2022.day21;

/**
 * A monkey's value as an expression.
 *
 * @param lhs name of the monkey to yell the left-hand side value
 * @param operator the operator
 * @param rhs name of the monkey to yell the right-hand side value
 * @author Martijn van de Rijdt
 */
record ExpressionValue(String lhs, Operator operator, String rhs) implements Value {
    /**
     * Parses the string representation of an expression.
     * 
     * @param stringRepresentation string representation of an expression; for example: "pppw + sjmn"
     * @return expression value
     */
    static ExpressionValue parse(String stringRepresentation) {
        var parts = stringRepresentation.split(" ");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Unable to parse: " + stringRepresentation);
        }
        return new ExpressionValue(parts[0], Operator.parse(parts[1]), parts[2]);
    }
}
