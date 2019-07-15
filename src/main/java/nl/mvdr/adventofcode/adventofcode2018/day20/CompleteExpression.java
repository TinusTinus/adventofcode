package nl.mvdr.adventofcode.adventofcode2018.day20;

/**
 * A complete {@link RoomMapExpression}.
 *
 * @author Martijn van de Rijdt
 */
class CompleteExpression implements RoomMapExpression {
    private final RoomMapExpression expression;
    
    CompleteExpression(RoomMapExpression expression) {
        super();
        this.expression = expression;
    }

    @Override
    public String toString() {
        return "^" + expression + "$";
    }
}
