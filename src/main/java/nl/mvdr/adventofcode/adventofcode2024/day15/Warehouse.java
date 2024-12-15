package nl.mvdr.adventofcode.adventofcode2024.day15;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import nl.mvdr.adventofcode.point.Direction;
import nl.mvdr.adventofcode.point.Point;

record Warehouse(Set<Point> walls, Set<Box> boxes, Point robot) {
    static Warehouse parse(List<String> lines) {
        Set<Point> walls = new HashSet<>();
        Set<Box> boxes = new HashSet<>();
        Set<Point> robots = new HashSet<>();
        
        Point.parse2DMap(lines, (point, c) -> {
            if (c == '#' ) {
                walls.add(point);
            } else if (c == 'O') {
                boxes.add(new Box(point, 1));
            } else if (c == '[') {
                boxes.add(new Box(point, 2));
            } else if (c == '@') {
                robots.add(point);
            } else if (c != '.' && c != ']') {
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
                .mapToInt(box -> box.location().y() * 100 + box.location().x())
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
        Optional<Warehouse> result = null;
        if (walls.contains(point)) {
            result = Optional.empty();
        } else {
            // See if any boxes are in the way.
            Optional<Box> optionalBox = boxes.stream()
                    .filter((box -> box.occupiesSpace(point)))
                    .findFirst();
            if (optionalBox.isEmpty()) {
                result = Optional.of(this);
            } else {
                var box = optionalBox.orElseThrow();
                
                Set<Point> spacesToClear;
                if (direction == Direction.LEFT) {
                    spacesToClear = Set.of(Direction.LEFT.move(point));
                } else if (direction == Direction.RIGHT) {
                    spacesToClear = Set.of(Direction.RIGHT.move(point, box.width()));
                } else if (direction.isVertical()) {
                    spacesToClear = box.spaces()
                            .stream()
                            .map(direction::move)
                            .collect(Collectors.toSet());
                } else {
                    throw new IllegalArgumentException("No diagonals allowed");
                }
                
                result = Optional.of(this);
                for (var space : spacesToClear) {
                    result = result.flatMap(warehouse -> warehouse.clear(space, direction));
                }
                result = result.map(warehouse -> warehouse.moveBox(box, direction));
            }
        }
        return result;
    }
    
    /// Moves the given box. Note that this method does not perform any argument checking.
    private Warehouse moveBox(Box box, Direction direction) {
        Set<Box> newBoxes = new HashSet<>(boxes);
        newBoxes.remove(box);
        newBoxes.add(box.move(direction));
        return new Warehouse(walls, newBoxes, robot);
    }
    
    /// Moves the given robot. Note that this method does not perform any argument checking.
    /// @param targetLocation target box location; must not be a wall or box location
    /// @return updated warehouse
    private Warehouse moveRobot(Point targetLocation) {
        return new Warehouse(walls, boxes, targetLocation);
    }
}
