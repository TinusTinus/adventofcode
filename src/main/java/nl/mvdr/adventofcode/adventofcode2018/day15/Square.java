package nl.mvdr.adventofcode.adventofcode2018.day15;

import java.util.stream.Stream;

/**
 * A static element of the map.
 *
 * @author Martijn van de Rijdt
 */
enum Square {
    /** A wall. */
    WALL('#'),
    /** An open area. */
    OPEN_AREA('.');
    
    /** Character representation of this square in the puzzle input. */
    private final char representation;
    
    /**
     * Constructor.
     * 
     * @param representation character representation of this square in the puzzle input
     */
    Square(char representation) {
        this.representation = representation;
    }
    
    /**
     * Parses the given character into a square.
     * 
     * @param representation character representation
     * @return corresponding square
     */
    static Square of(char representation) { 
        return Stream.of(Square.values())
                .filter(race -> race.representation == representation)
                .findFirst()
                .get();
    }
    
    @Override
    public String toString() {
        return "" + representation;
    }
}
