package nl.mvdr.adventofcode.adventofcode2024.day16;

import nl.mvdr.adventofcode.point.Direction;
import nl.mvdr.adventofcode.point.Point;

record PointAndDirection(Point point, Direction direction) {
    PointAndDirection move() {
        return new PointAndDirection(direction.move(point), direction);
    }
    
    PointAndDirection turnClockwise() {
        return new PointAndDirection(point, direction.turnClockwise());
    }
    
    PointAndDirection turnCounterClockwise() {
        return new PointAndDirection(point, direction.turnCounterClockwise());
    }
}
