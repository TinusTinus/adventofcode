package nl.mvdr.adventofcode.adventofcode2022.day22;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * Type of tiles on the map.
 *
 * @author Martijn van de Rijdt
 */
enum Terrain {
    /**
     * An open tile on which we can move.
     */
    OPEN_TILE('.'),
    /**
     * A tile which we cannot enter.
     */
    SOLID_WALL('#');
    
    private final char characterRepresentation;
    
    /**
     * Constructor.
     * 
     * @param characterRepresentation character representation of this type of tile in the puzzle input
     */
    Terrain(char characterRepresentation) {
        this.characterRepresentation = characterRepresentation;
    }
    
    /**
     * Parses a single tile from the puzzle input.
     * 
     * @param c character representation of the tile
     * @return terrain
     */
    static Optional<Terrain> parse(char c) {
        var result = Stream.of(Terrain.values())
                    .filter(terrain -> terrain.characterRepresentation == c)
                    .findFirst();
        if (result.isEmpty() && c != ' ') {
            throw new IllegalArgumentException("Unexpected input: " + c);
        }
        return result;
    }
}
