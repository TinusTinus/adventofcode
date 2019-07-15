package nl.mvdr.adventofcode.adventofcode2018.day20;

/**
 * A complete {@link RoomMapExpression}.
 *
 * @author Martijn van de Rijdt
 */
class CompleteExpression implements RoomMapExpression {
    private final RoomMapExpression expression;
    
    private CompleteExpression(RoomMapExpression expression) {
        super();
        this.expression = expression;
    }

    /**
     * Parses the given string into a {@link CompleteExpression}.
     * 
     * @param expressionString string representation of the expression to be parsed
     * @return expression
     */
    static CompleteExpression parse(String expressionString) {
        return new CompleteExpression(EmptyExpression.getInstance()); // TODO
    }
    
    @Override
    public String toString() {
        return "^" + expression + "$";
    }
}
