package nl.mvdr.adventofcode.adventofcode2023.day13;

import java.util.stream.Stream;

/**
 * Type of terrain on Lava Island.
 *
 * @author Martijn van de Rijdt
 */
enum Terrain {
    ASH('.'),
    ROCK('#');
    
    private final char representation;
    
    /**
     * Constructor.
     * 
     * @param representation single-character representation of this type of terrain in the puzzle input
     */
    Terrain(char representation) {
        this.representation = representation;
    }
    
    static Terrain of(char representation) {
        return Stream.of(values())
                .filter(terrain -> terrain.representation == representation)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Not a valid terrain: " + representation));
    }
}
