package nl.mvdr.adventofcode.adventofcode2024.day21;

import java.util.Optional;
import java.util.stream.Stream;

import nl.mvdr.adventofcode.point.Direction;
import nl.mvdr.adventofcode.point.Point;

/// Self-referential generic interface for keypad button enums.
interface KeypadButton<B extends Enum<B> & KeypadButton<B>> {
    
    Point getLocation();
    
    private static <T extends Enum<T> & KeypadButton<T>> Optional<T> buttonAt(Point location, Class<T> buttonType) {
        return Stream.of(buttonType.getEnumConstants())
                .filter(button -> button.getLocation().equals(location))
                .findFirst();
    }
    
    default Optional<B> neighbouringButton(Direction direction) {
        return buttonAt(direction.move(getLocation()), (Class<B>)getClass());
    }
}
