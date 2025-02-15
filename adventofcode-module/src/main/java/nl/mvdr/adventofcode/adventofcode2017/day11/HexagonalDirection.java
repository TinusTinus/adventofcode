package nl.mvdr.adventofcode.adventofcode2017.day11;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A direction in an infinite grid of hexagons.
 *
 * @author Martijn van de Rijdt
 */
enum HexagonalDirection {
    NORTH("n"),
    NORTHEAST("ne"),
    SOUTHEAST("se"),
    SOUTH("s"),
    SOUTHWEST("sw"),
    NORTHWEST("nw");
    
    private final String representation;

    /**
     * Parses the given input into a list of directions.
     * 
     * @param lines puzzle input
     * @return list of directions
     */
    static List<HexagonalDirection> parse(Stream<String> lines) {
        String inputText = lines.findFirst().orElseThrow();
        return Stream.of(inputText.split(","))
                .map(HexagonalDirection::parseDirection)
                .collect(Collectors.toList());
    }
    
    /**
     * Parses the given string into a direction.
     * 
     * @param string string to be parsed
     * @return direction
     */
    private static HexagonalDirection parseDirection(String string) {
        return Stream.of(values())
                .filter(direction -> direction.representation.equals(string))
                .findFirst()
                .orElseThrow();
    }
    
    /**
     * Constructor.
     * 
     * @param representation string representation of this input
     */
    HexagonalDirection(String representation) {
        this.representation = representation;
    }
    
    @Override
    public String toString() {
        return representation;
    }
}
