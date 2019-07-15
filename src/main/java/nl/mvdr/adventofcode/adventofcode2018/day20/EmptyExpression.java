package nl.mvdr.adventofcode.adventofcode2018.day20;

import java.util.Set;

import nl.mvdr.adventofcode.adventofcode2018.point.Point;

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
    public Set<Point> apply(Set<Point> points, RoomMap map) {
        return points;
    }
    
    @Override
    public String toString() {
        return "";
    }
}
