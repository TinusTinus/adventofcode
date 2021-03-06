package nl.mvdr.adventofcode.adventofcode2020.day18;

/**
 * Representation of an operation expression (either an addition or a
 * multiplication).
 *
 * @author Martijn van de Rijdt
 */
record Operation(Expression lhs, Operator operator, Expression rhs) implements Expression {
    
    @Override
    public long evaluate() {
        return operator.apply(lhs.evaluate(), rhs.evaluate());
    }
    
    @Override
    public String toString() {
        return "(" + lhs + " " + operator.getRepresentation() + " " + rhs + ")";
    }
}
