package nl.mvdr.adventofcode.adventofcode2024.day15;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
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
    
    int sumOfBoxGPS() {
        return boxes.stream()
                .mapToInt(box -> box.y() * 100 + box.x())
                .sum();
    }
    
    /// Performs an attempt to move the robot in the given direction.
    Warehouse attemptMove(Direction direction) {
        var targetLocation = direction.move(robot);
        return clear(targetLocation, direction)
                .map(warehouse -> warehouse.moveRobot(targetLocation))
                .orElse(this);
    }
    
    /// Clears the given space, by shoving any boxes into the given direction.
    /// @return updated warehouse, or [Optional#empty()] if there is a wall in the way
    private Optional<Warehouse> clear(Point point, Direction direction) {
        Optional<Warehouse> result;
        if (walls.contains(point)) {
            result = Optional.empty();
        } else if (boxes.contains(point)) {
            result = clear(direction.move(point), direction)
                    .map(warehouse -> warehouse.moveBox(point, direction.move(point)));
        } else {
            result = Optional.of(this);
        }
        return result;
    }
    
    /// Moves the given box. Note that this method does not perform any argument checking.
    /// @param currentLocation current box location; must be a point occurring in [#boxes]
    /// @param targetLocation target box location; must not be a wall, box or robot location
    /// @return updated warehouse
    private Warehouse moveBox(Point currentLocation, Point targetLocation) {
        Set<Point> newBoxes = new HashSet<>(boxes);
        newBoxes.remove(currentLocation);
        newBoxes.add(targetLocation);
        return new Warehouse(walls, newBoxes, robot);
    }
    
    /// Moves the given robot. Note that this method does not perform any argument checking.
    /// @param targetLocation target box location; must not be a wall or box location
    /// @return updated warehouse
    private Warehouse moveRobot(Point targetLocation) {
        return new Warehouse(walls, boxes, targetLocation);
    }
}
