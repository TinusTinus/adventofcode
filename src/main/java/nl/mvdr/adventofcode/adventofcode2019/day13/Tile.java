package nl.mvdr.adventofcode.adventofcode2019.day13;

import java.util.stream.Stream;

/**
 * Representation of a tile.
 *
 * @author Martijn van de Rijdt
 */
enum Tile {
    /** No game object appears in this tile. */
    EMPTY(0L, ' '),
    /** Walls are indestructible barriers. */
    WALL(1L, '#'),
    /** Blocks can be broken by the ball. */
    BLOCK(2L, 'x'),
    /** A horizontal paddle tile. The paddle is indestructible. */
    PADDLE(3L, '-'),
    /** The ball moves diagonally and bounces off objects. */
    BALL(4L, '*');
    
    private final long code;
    private final char representation;
    
    /**
     * Gets the tile associated with the given code.
     * 
     * @param code intcode representation of a tile
     * @return corresponding tile
     */
    static Tile of(long code) {
        return Stream.of(values())
                .filter(value -> value.code == code)
                .findFirst()
                .orElseThrow();
    }
    
    /**
     * Constructor.
     * 
     * @param code representation of this tile in the Intcode program
     * @param representation single character representation of this type of tile
     */
    Tile(long code, char representation) {
        this.code = code;
        this.representation = representation;
    }
    
    char getRepresentation() {
        return representation;
    }
}
