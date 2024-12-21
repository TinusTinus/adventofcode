package nl.mvdr.adventofcode.adventofcode2024.day21;

import java.util.Optional;
import java.util.stream.Stream;

import nl.mvdr.adventofcode.point.Direction;
import nl.mvdr.adventofcode.point.Point;

enum DirectionalKeypadButton {
    
    UP(1, 0),
    A(2, 0),
    LEFT(0, 1),
    DOWN(1, 1),
    RIGHT(2, 1);
    
    private final Point location;
    
    private DirectionalKeypadButton(int x, int y) {
        this.location = new Point(x, y);
    }
    
    private static Optional<DirectionalKeypadButton> buttonAt(Point location) {
        return Stream.of(DirectionalKeypadButton.values())
                .filter(button -> button.location.equals(location))
                .findFirst();
    }
    
    Optional<DirectionalKeypadButton> neighbouringButton(Direction direction) {
        return buttonAt(direction.move(location));
    }
}
