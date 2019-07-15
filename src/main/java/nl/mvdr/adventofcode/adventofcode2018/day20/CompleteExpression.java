package nl.mvdr.adventofcode.adventofcode2018.day20;

import java.util.Set;

import nl.mvdr.adventofcode.adventofcode2018.point.Point;

/**
 * A complete {@link RoomMapExpression}.
 *
 * @author Martijn van de Rijdt
 */
class CompleteExpression implements RoomMapExpression {
    private final RoomMapExpression expression;
    
    /**
     * Constructor.
     * 
     * @param expression expression
     */
    CompleteExpression(RoomMapExpression expression) {
        super();
        this.expression = expression;
    }

    @Override
    public Set<Point> apply(Set<Point> points, RoomMap map) {
        Set<Point> result = expression.apply(points, map);
        
        map.complete();
        
        return result;
    }
    
    @Override
    public String toString() {
        return "^" + expression + "$";
    }
}
