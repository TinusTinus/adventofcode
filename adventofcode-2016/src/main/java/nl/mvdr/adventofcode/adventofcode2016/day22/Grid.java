package nl.mvdr.adventofcode.adventofcode2016.day22;

import java.util.Set;
import java.util.stream.Collectors;
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
    
    Stream<Grid> step() {
        var viablePairs = nodes.stream()
                .flatMap(a -> neighbours(a).map(b -> new NodePair(a, b)))
                .filter(NodePair::isViable)
                .collect(Collectors.toSet());
        
        return Stream.of(this); // TODO
    }
    
    // TODO the following could be made more efficient by storing nodes in a map, indexed by their location
    private Stream<Node> neighbours(Node node) {
        var neighbourLocations = node.location().neighbours();
        
        return nodes.stream()
                .filter(otherNode -> neighbourLocations.contains(otherNode.location()));
    }
}
