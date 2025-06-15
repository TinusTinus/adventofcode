package nl.mvdr.adventofcode.adventofcode2016.day22;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import nl.mvdr.adventofcode.point.Point;

public record Grid(Map<Point, Node> nodes, Point goalDataLocation) {
    static Grid parse(Stream<String> lines) {
        var nodes = Node.parse(lines)
                .collect(Collectors.toMap(Node::location, Function.identity()));
        
        var goalDataLocation = nodes.values()
                .stream()
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
        return nodes.entrySet()
                .stream()
                .flatMap(entry -> entry.getKey()
                        .neighbours()
                        .stream()
                        .map(nodes::get)
                        .filter(Objects::nonNull)
                        .map(b -> new NodePair(entry.getValue(), b)))
                .filter(NodePair::isViable)
                .map(pair -> moveData(pair.a(), pair.b()));
    }
    
    private Grid moveData(Node source, Node target) {
        Map<Point, Node> newNodes = new HashMap<>(nodes);
        
        newNodes.put(source.location(), new Node(source.location(), source.size(), 0));
        newNodes.put(target.location(), new Node(target.location(), target.size(), target.used() + source.used()));
        
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
