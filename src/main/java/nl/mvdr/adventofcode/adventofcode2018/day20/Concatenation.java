package nl.mvdr.adventofcode.adventofcode2018.day20;

/**
 * Concatentation of two expressions.
 *
 * @author Martijn van de Rijdt
 */
class Concatenation implements RoomMapExpression {
    /** Left-hand side. */
    private final RoomMapExpression lhs;
    /** Right-hand side. */
    private final RoomMapExpression rhs;
    
    /**
     * Constructor.
     * 
     * @param lhs left-hand side
     * @param rhs right-hand side
     */
    Concatenation(RoomMapExpression lhs, RoomMapExpression rhs) {
        super();
        this.lhs = lhs;
        this.rhs = rhs;
    }
    
    @Override
    public String toString() {
        return "" + lhs + rhs;
    }
}
