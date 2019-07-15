package nl.mvdr.adventofcode.adventofcode2018.day20;

import java.util.Set;

import nl.mvdr.adventofcode.adventofcode2018.point.Point;

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
    public Set<Point> apply(Set<Point> points, RoomMap map) {
        Set<Point> result = lhs.apply(points, map);
        result = rhs.apply(result, map);
        return result;
    }
    
    @Override
    public String toString() {
        return "" + lhs + rhs;
    }
}
