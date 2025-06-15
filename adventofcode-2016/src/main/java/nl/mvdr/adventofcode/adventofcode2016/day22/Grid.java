package nl.mvdr.adventofcode.adventofcode2016.day22;

import java.util.HashSet;
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
    
    Stream<Grid> step() {
        return nodes.stream()
                .flatMap(a -> nodes.stream().map(b -> new NodePair(a, b)))
                .filter(NodePair::isViable)
                .filter(pair -> pair.a().location().neighbours().contains(pair.b().location()))
                .map(pair -> moveData(pair.a(), pair.b()));
    }
    
    private Grid moveData(Node source, Node target) {
        Set<Node> newNodes = new HashSet<>(nodes);
        
        newNodes.remove(source);
        newNodes.add(new Node(source.location(), source.size(), 0));
        
        newNodes.remove(target);
        newNodes.add(new Node(target.location(), target.size(), target.used() + source.used()));
        
        Point newGoalDataLocation;
        if (goalDataLocation.equals(source.location())) {
            // Goal data is being copied from source to target
            newGoalDataLocation = target.location();
        } else {
            newGoalDataLocation = goalDataLocation;
        }
        
        return new Grid(newNodes, newGoalDataLocation);
    }
}
