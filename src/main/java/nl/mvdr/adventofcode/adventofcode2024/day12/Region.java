package nl.mvdr.adventofcode.adventofcode2024.day12;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import nl.mvdr.adventofcode.point.Direction;
import nl.mvdr.adventofcode.point.Orientation;
import nl.mvdr.adventofcode.point.Point;

record Region(char plant, Set<Point> plots) {

    int cost() {
        return Math.multiplyExact(area(), perimeter());
    }
    
    private int area() {
        return plots.size();
    }

    private int perimeter() {
        return findPerimeterElements().size();
    }

    private Set<PerimeterElement> findPerimeterElements() {
        return plots.stream()
                .flatMap(plot -> Stream.of(Direction.values())
                        .filter(direction -> direction.getOrientation() != Orientation.DIAGONAL)
                        .filter(direction -> !plots.contains(direction.move(plot)))
                        .map(direction -> new PerimeterElement(plot, direction)))
                .collect(Collectors.toSet());
    }
    
    int costWithDiscount() {
        return Math.multiplyExact(area(), sides());
    }
    
    
    private int sides() {
        // Find all elements of the perimeter
        var perimeterElements = new HashSet<>(findPerimeterElements());
        
        // Now group them into sides.
        // This might not be the most elegant solution, but it is correct.
        List<PerimeterElement> side = new ArrayList<>();
        
        PerimeterElement firstElement = perimeterElements.iterator().next();
        perimeterElements.remove(firstElement);
        side.add(firstElement);
        
        var result = 1;
        while (!perimeterElements.isEmpty()) {
            var element = side.getFirst();
            var plot = element.plot();
            var direction = element.side();
            
            var nextElement = new PerimeterElement(direction.turnCounterClockwise().move(plot), direction);
            if (perimeterElements.remove(nextElement)) {
                side.add(0, nextElement);
            } else {
                element = side.getLast();
                plot = element.plot();
                direction = element.side();
                nextElement = new PerimeterElement(direction.turnClockwise().move(plot), direction);
                if (perimeterElements.remove(nextElement)) {
                    side.add(nextElement);
                } else {
                    result++;
                    nextElement = perimeterElements.iterator().next();
                    perimeterElements.remove(nextElement);
                    side = new ArrayList<>();
                    side.add(nextElement);
                }
            }
        }
        
        return result;
    }
}
