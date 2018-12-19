package nl.mvdr.adventofcode.adventofcode2018.day18;

import java.util.stream.Stream;

/**
 * Type of an acre of land in a lumber collection area.
 *
 * @author Martijn van de Rijdt
 */
enum AcreType {
    
    /** An open acre. Can fill with trees and turn into {@link #TREES}. */
    OPEN_GROUND('.'),
    
    /** An acre filled with trees. Can be converted into a {@link #LUMBERYARD}. */
    TREES('|'),
    
    /** An acre containing a lumberyard. Can be cleared to {@link #OPEN_GROUND}. */
    LUMBERYARD('#');
    
    /** Representation of this type of acre. */
    private final char character;
    
    /**
     * Constructor.
     * 
     * @param character representation of this type of acre
     */
    AcreType(char character) {
        this.character = character;
    }
    
    public char getCharacter() {
        return character;
    }
    
    /**
     * Parses the character representation of an acre type into an {@link AcreType} enum value.
     * 
     * @param c character
     * @return enum value
     */
    public static AcreType fromCharacter(char c) {
        return Stream.of(AcreType.values())
                .filter(type -> type.getCharacter() == c)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("Unexpected character: " + c));
    }
}
