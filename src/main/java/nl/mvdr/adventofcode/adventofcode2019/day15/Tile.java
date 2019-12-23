package nl.mvdr.adventofcode.adventofcode2019.day15;

import java.util.stream.Stream;

/**
 * Representation of the different possible tiles on the map.
 *
 * @author Martijn van de Rijdt
 */
enum Tile {
    WALL(0L),
    EMPTY_SPACE(1L),
    OXYGEN_SYSTEM(2L);

    /**
     * Finds the tile value with the given code.
     * 
     * @param code Intcode representation of the tile
     * @return tile
     */
    static Tile of(long code) {
        return Stream.of(values())
                .filter(value -> value.code == code)
                .findFirst()
                .orElseThrow();
    }
    
    private final long code;
    
    Tile(long code) {
        this.code = code;
    }
}
