package nl.mvdr.adventofcode.adventofcode2024.day21;

import java.util.Optional;
import java.util.stream.Stream;

import nl.mvdr.adventofcode.point.Point;

enum NumericKeypadButton implements KeypadButton<NumericKeypadButton> {
    
    KEY_7('7', 0, 0),
    KEY_8('8', 1, 0),
    KEY_9('9', 2, 0),
    KEY_4('4', 0, 1),
    KEY_5('5', 1, 1),
    KEY_6('6', 2, 1),
    KEY_1('1', 0, 2),
    KEY_2('2', 1, 2),
    KEY_3('3', 2, 2),
    KEY_0('0', 1, 3),
    KEY_A('A', 2, 3);
    
    private final char character;
    private final Point location;
    
    private NumericKeypadButton(char character, int x, int y) {
        this.character = character;
        this.location = new Point(x, y);
    }
    
    static NumericKeypadButton of(char c) {
        return Stream.of(NumericKeypadButton.values())
                .filter(button -> button.character == c)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No button found for " + c));
    }
    
    static Optional<NumericKeypadButton> buttonAt(Point location) {
        return Stream.of(NumericKeypadButton.values())
                .filter(button -> button.location.equals(location))
                .findFirst();
    }
    
    char getCharacter() {
        return character;
    }
    
    @Override
    public Point getLocation() {
        return location;
    }
}
