package nl.mvdr.adventofcode.adventofcode2018.day20;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import nl.mvdr.adventofcode.adventofcode2018.point.Point;

/**
 * Concatentation of a number of expressions.
 *
 * @author Martijn van de Rijdt
 */
class Concatenation implements RoomMapExpression {
    
    /** The elements of this concatenation. */
    private final List<? extends RoomMapExpression> elements;
    
    /**
     * Convenience constructor.
     * 
     * @param lhs left-hand side
     * @param rhs right-hand side
     */
    Concatenation(RoomMapExpression lhs, RoomMapExpression rhs) {
        this(List.of(lhs, rhs));
    }
    
    /**
     * Constructor.
     * 
     * @param elements list of elements of the concatenation
     */
    public Concatenation(List<? extends RoomMapExpression> elements) {
        super();
        this.elements = elements;
    }

    @Override
    public Set<Point> apply(Set<Point> points, RoomMap map) {
        Set<Point> result = points;
        
        for (RoomMapExpression element : elements) {
            result = element.apply(result, map);
        }
        
        return result;
    }
    
    @Override
    public String toString() {
        return elements.stream()
                .map(Object::toString)
                .collect(Collectors.joining());
    }
}
