package nl.mvdr.adventofcode.adventofcode2023.day02;

import java.util.stream.Stream;

/**
 * Possible colours of a cube.
 *
 * @author Martijn van de Rijdt
 */
enum Color {
    RED("red", 12),
    GREEN("green", 13),
    BLUE("blue", 14);
    
    private final String representation;
    private final int maximum;
    
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
     * @param maximum the maximum available number of cubes of this color, in part 1 of the puzzle
     */
    Color(String representation, int maximum) {
        this.representation = representation;
        this.maximum = maximum;
    }
    
    /**
     * @return the maximum available number of cubes of this color, in part 1 of the puzzle
     */
    int getMaximum() {
        return maximum;
    }
}
