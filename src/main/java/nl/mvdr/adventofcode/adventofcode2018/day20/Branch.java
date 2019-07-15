package nl.mvdr.adventofcode.adventofcode2018.day20;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import nl.mvdr.adventofcode.adventofcode2018.point.Point;

/**
 * A branch, consisting of two options.
 *
 * @author Martijn van de Rijdt
 */
public class Branch implements RoomMapExpression {
    
    private final List<RoomMapExpression> options;
    
    /**
     * Constructor.
     * 
     * @param options options
     */
    Branch(List<RoomMapExpression> options) {
        super();
        this.options = options;
    }

    @Override
    public Set<Point> apply(Set<Point> points, RoomMap map) {
        return options.stream()
                .map(option -> option.apply(points, map))
                .flatMap(Set::stream)
                .collect(Collectors.toSet());
    }
    
    @Override
    public String toString() {
        return "(" + options.stream().map(Object::toString).collect(Collectors.joining("|")) + ")";
    }
}
