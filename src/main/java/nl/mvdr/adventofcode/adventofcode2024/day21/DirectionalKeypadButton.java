package nl.mvdr.adventofcode.adventofcode2024.day21;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import nl.mvdr.adventofcode.point.Direction;
import nl.mvdr.adventofcode.point.Point;

enum DirectionalKeypadButton implements KeypadButton<DirectionalKeypadButton> {
    
    UP(Direction.UP, 1, 0),
    A(null, 2, 0),
    LEFT(Direction.LEFT, 0, 1),
    DOWN(Direction.DOWN, 1, 1),
    RIGHT(Direction.RIGHT, 2, 1);
    
    private final Direction direction;
    private final Point location;
    
    private DirectionalKeypadButton(Direction direction, int x, int y) {
        this.direction = direction;
        this.location = new Point(x, y);
    }

    Direction getDirection() {
        return direction;
    }
    
    @Override
    public Point getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return Optional.ofNullable(direction)
                .map(Direction::toString)
                .orElse("A");
    }
    
    static Stream<List<DirectionalKeypadButton>> getAllPermutations(int length) {
        return switch(length) {
            case 0 -> Stream.of(List.of());
            default -> getAllPermutations(length - 1)
                    .flatMap(permutation -> Stream.of(values())
                            .map(value -> (Stream.concat(Stream.of(value), permutation.stream())).toList()));
        };
    }
}
