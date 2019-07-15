package nl.mvdr.adventofcode.adventofcode2018.day20;

import java.util.HashSet;
import java.util.Set;

import nl.mvdr.adventofcode.adventofcode2018.point.Point;

/**
 * A branch, consisting of two options.
 *
 * @author Martijn van de Rijdt
 */
public class Branch implements RoomMapExpression {
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
    Branch(RoomMapExpression lhs, RoomMapExpression rhs) {
        super();
        this.lhs = lhs;
        this.rhs = rhs;
    }
    
    @Override
    public Set<Point> apply(Set<Point> points, RoomMap map) {
        HashSet<Point> result = new HashSet<>();
        result.addAll(lhs.apply(points, map));
        result.addAll(rhs.apply(points, map));
        return result;
    }
    
    @Override
    public String toString() {
        return "(" + lhs + "|" + rhs + ")";
    }
}
