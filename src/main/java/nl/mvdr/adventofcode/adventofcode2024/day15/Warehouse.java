package nl.mvdr.adventofcode.adventofcode2024.day15;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
            Optional<Box> boxInTheWay = boxes.stream()
                    .filter(box -> box.occupies(point))
                    .findFirst();
            if (boxInTheWay.isEmpty()) {
                result = Optional.of(this);
            } else {
                var box = boxInTheWay.orElseThrow();
                result = Optional.of(this);
                for (var space : box.spacesInFront(direction)) {
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
    private Warehouse moveRobot(Point targetLocation) {
        return new Warehouse(walls, boxes, targetLocation);
    }
    
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Warehouse: \n");

        int minX = Point.minX(walls);
        int maxX = Point.maxX(walls);
        int minY = Point.minY(walls);
        int maxY = Point.maxY(walls);
        
        for (int y = minY; y != maxY + 1; y++) {
            for (int x = minX; x != maxX + 1; x++) {
                var point = new Point(x, y);
                
                if (walls.contains(point)) {
                    builder.append("#");
                } else if (robot.equals(point)) {
                    builder.append("@");
                } else {
                    var optionalBox = boxes.stream()
                            .filter(box -> box.occupies(point))
                            .findFirst();
                    if (optionalBox.isPresent()) {
                        var box = optionalBox.orElseThrow();
                        if (box.width() == 1) {
                            builder.append("O");
                        } else if (box.width() == 2) {
                            if (box.location().equals(point)) {
                                builder.append("[");
                            } else {
                                builder.append("]");
                            }
                        } else {
                            throw new IllegalStateException("Unexpected box width: " + box.width());
                        }
                    } else {
                        builder.append(".");
                    }
                }
            }
            builder.append("\n");
        }
        return builder.toString();
    }
}
