package nl.mvdr.adventofcode.adventofcode2024.day21;

import java.util.Optional;
import java.util.stream.Stream;

import nl.mvdr.adventofcode.point.Direction;
import nl.mvdr.adventofcode.point.Point;

/// Self-referential generic interface for keypad button enums.
interface KeypadButton<B extends Enum<B> & KeypadButton<B>> {
    
    Point getLocation();
    
    default Optional<B> neighbouringButton(Direction direction) {
        var neighbourLocation = direction.move(getLocation());
        return Stream.of(getClass().getEnumConstants())
                .map(value -> (B)value)
                .filter(button -> button.getLocation().equals(neighbourLocation))
                .findFirst();
    }
}