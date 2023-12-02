package nl.mvdr.adventofcode.adventofcode2023.day02;

import java.util.stream.Stream;

/**
 * Possible colours of a cube.
 *
 * @author Martijn van de Rijdt
 */
enum Color {
    RED("red"),
    GREEN("green"),
    BLUE("blue");
    
    private final String representation;
    
    /**
     * Finds the cube color represented by the given string.
     * 
     * @param text text representation of a color
     * @return color
     */
    static Color of(String text) {
        return Stream.of(values())
                .filter(color -> color.representation.equals(text))
                .findFirst()
                .orElseThrow();
    }
    
    /**
     * Constructor.
     * 
     * @param representation string representation of this color (in the puzzle input)
     */
    Color(String representation) {
        this.representation = representation;
    }
}
