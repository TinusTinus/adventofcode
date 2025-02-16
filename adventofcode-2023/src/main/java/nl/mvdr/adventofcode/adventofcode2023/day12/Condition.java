package nl.mvdr.adventofcode.adventofcode2023.day12;

import java.util.List;
import java.util.stream.Stream;

/**
 * Condition of a spring.
 *
 * @author Martijn van de Rijdt
 */
enum Condition {
    
    OPERATIONAL('.'),
    DAMAGED('#'),
    UNKNOWN('?');
    
    private final char representation;
    
    /**
     * Constructor.
     * 
     * @param representation single-character representation of the spring condition
     */
    Condition(char representation) {
        this.representation = representation;
    }
    
    /**
     * Parses the string representation of the conditions of a row of springs.
     * 
     * @param representation string representation of a row, for example: "???.###"
     * @return list of conditions
     */
    static List<Condition> parse(String representation) {
        return representation.chars()
                .mapToObj(c -> of((char) c))
                .toList();
    }
    
    /**
     * Finds the condition represented by the given character.
     * 
     * @param representation single-character representation of a spring condition
     * @return the condition
     */
    private static Condition of(char representation) {
        return Stream.of(values()).filter(condition -> condition.representation == representation)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Not a valid spring condition: " + representation));
    }

    
    @Override
    public String toString() {
        return "" + representation;
    }
}
