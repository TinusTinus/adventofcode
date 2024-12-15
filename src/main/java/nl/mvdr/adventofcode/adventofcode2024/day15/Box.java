package nl.mvdr.adventofcode.adventofcode2024.day15;

import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import nl.mvdr.adventofcode.point.Direction;
import nl.mvdr.adventofcode.point.Point;

record Box(Point location, int width) {
    Box move(Direction direction) {
        return new Box(direction.move(location), width);
    }
    
    private Set<Point> spaces() {
        return IntStream.range(0, width)
                .mapToObj(offset -> new Point(location.x() + offset, location.y()))
                .collect(Collectors.toSet());
    }
    
    boolean occupies(Point space) {
        return spaces().contains(space);
    }
    
    /// Returns the spaces directly in front of this box.
    /// That is, the spaces which need to be clear before the box can be shoved in the given direction.
    Set<Point> spacesInFront(Direction direction) {
        var boxSpaces = spaces();
        return boxSpaces.stream()
                .map(space -> direction.move(space))
                .filter(Predicate.not(boxSpaces::contains))
                .collect(Collectors.toSet());
    }
}
