package nl.mvdr.adventofcode.adventofcode2018.day20;

/**
 * Empty expression.
 *
 * @author Martijn van de Rijdt
 */
class EmptyExpression implements RoomMapExpression {
    private static final EmptyExpression INSTANCE = new EmptyExpression();

    /** @return singleton instance */
    static EmptyExpression getInstance() {
        return INSTANCE;
    }

    private EmptyExpression() {
        // Private constructor to prevent singleton instantiation.
    }
    
    @Override
    public String toString() {
        return "";
    }
}
