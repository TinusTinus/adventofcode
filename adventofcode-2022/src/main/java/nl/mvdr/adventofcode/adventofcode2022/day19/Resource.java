package nl.mvdr.adventofcode.adventofcode2022.day19;

import java.util.stream.Stream;

/**
 * A resource relevant to this puzzle.
 *
 * @author Martijn van de Rijdt
 */
enum Resource {
    /** A unit of ore. */
    ORE,
    /** A unit of clay. */
    CLAY,
    /** A unit of obsidian. */
    OBSIDIAN,
    /** An open geode. */
    GEODE;
    
    /**
     * Parses a textual representation of a resource.
     * 
     * @param text text representation of the resource in lowercase; for example: "ore"
     * @return the resource corresponding to the given text representation
     */
    static Resource parse(String text) {
        return Stream.of(Resource.values())
                .filter(resource -> resource.toString().equals(text))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Not a valid resource: " + text));
    }
    
    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
