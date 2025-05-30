package nl.mvdr.adventofcode.adventofcode2016.day18;

import java.util.stream.Stream;

enum Tile {
    TRAP('^'),
    SAFE('.');
    
    private final char representation;
    
    private Tile(char representation) {
        this.representation = representation;
    }
    
    static Tile parse(char representation) {
        return Stream.of(Tile.values())
                .filter(tile -> tile.representation == representation)
                .reduce((_, _) -> { throw new IllegalStateException(); })
                .orElseThrow();
    }
}
