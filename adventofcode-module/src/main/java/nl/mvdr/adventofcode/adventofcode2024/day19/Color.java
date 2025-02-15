package nl.mvdr.adventofcode.adventofcode2024.day19;

import java.util.stream.Stream;

enum Color {

    WHITE('w'),
    BLUE('u'),
    BLACK('b'),
    RED('r'),
    GREEN('g');
    
    private final char representation;
    
    private Color(char representation) {
        this.representation = representation;
    }
    
    static Color parse(char c) {
        return Stream.of(values())
                .filter(color -> color.representation == c)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Not a color: " + c));
    }
}
