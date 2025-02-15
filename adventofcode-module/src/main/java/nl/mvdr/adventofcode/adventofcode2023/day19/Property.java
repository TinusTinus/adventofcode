package nl.mvdr.adventofcode.adventofcode2023.day19;

import java.util.stream.Stream;

/**
 * Property of a part.
 *
 * @author Martijn van de Rijdt
 */
enum Property {
    
    EXTREMELY_COOL_LOOKING("x"),
    MUSICAL("m"),
    AERODYNAMIC("a"),
    SHINY("s");
    
    private final String representation;

    /**
     * Parses the string representation of a property.
     * 
     * @param representation string representation of a property: "x", "m", "a" or "s"
     * @return property
     */
    static Property parse(String representation) {
        return Stream.of(values())
                .filter(value -> value.representation.equals(representation))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Not a valid property: " + representation));
    }
    
    /**
     * Constructor.
     * 
     * @param representation string representation of this property name
     */
    Property(String representation) {
        this.representation = representation;
    }
    
    @Override
    public String toString() {
        return representation;
    }
}
