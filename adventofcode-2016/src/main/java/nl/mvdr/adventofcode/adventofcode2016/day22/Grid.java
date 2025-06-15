package nl.mvdr.adventofcode.adventofcode2016.day22;

import java.util.Set;
import java.util.stream.Stream;

import nl.mvdr.adventofcode.point.Point;

public record Grid(Set<Node> nodes, Point goalDataLocation) {
    static Grid parse(Stream<String> lines) {
        var nodes = Node.parse(lines);
        
        var goalDataLocation = nodes.stream()
                .map(Node::location)
                .filter(location -> location.y() == 0)
                .max(Point::compareTo)
                .orElseThrow();
        
        return new Grid(nodes, goalDataLocation);
    }
    
    boolean isGoalDataAccessible() {
        return Point.ORIGIN.equals(goalDataLocation);
    }
}
