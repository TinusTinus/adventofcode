package nl.mvdr.adventofcode.adventofcode2024.day15;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nl.mvdr.adventofcode.point.Direction;
import nl.mvdr.adventofcode.point.Point;

record Warehouse(Set<Point> walls, Set<Point> boxes, Point robot) {
    static Warehouse parse(List<String> lines) {
        Set<Point> walls = new HashSet<>();
        Set<Point> boxes = new HashSet<>();
        Set<Point> robots = new HashSet<>();
        
        Point.parse2DMap(lines, (point, c) -> {
            if (c == '#' ) {
                walls.add(point);
            } else if (c == 'O') {
                boxes.add(point);
            } else if (c == '@') {
                robots.add(point);
            } else if (c != '.') {
                throw new IllegalArgumentException("Unexpected character found in input: " + c);
            }
        });
        
        if (robots.size() != 1) {
            throw new IllegalArgumentException("Unable to parse warehouse. Expected 1 robot, but found: " + robots);
        }
        
        return new Warehouse(walls, boxes, robots.iterator().next());
    }
    
    /// Attempts to move the robot in the given direction.
    Warehouse attemptMove(Direction direction) {
        return this; // TODO implement!
    }
    
    int sumOfBoxGPS() {
        return boxes.stream()
                .mapToInt(box -> box.y() * 100 + box.x())
                .sum();
    }
}
