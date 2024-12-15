package nl.mvdr.adventofcode.adventofcode2024.day15;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import nl.mvdr.adventofcode.point.Direction;
import nl.mvdr.adventofcode.point.Point;

record Box(Point location, int width) {
    Box move(Direction direction) {
        return new Box(direction.move(location), width);
    }
    
    Set<Point> spaces() {
        return IntStream.range(0, width)
                .mapToObj(offset -> new Point(location.x() + offset, location.y()))
                .collect(Collectors.toSet());
    }
    
    boolean occupiesSpace(Point space) {
        return spaces().contains(space);
    }
}
