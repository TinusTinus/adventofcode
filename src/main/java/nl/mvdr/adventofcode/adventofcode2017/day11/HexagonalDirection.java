package nl.mvdr.adventofcode.adventofcode2017.day11;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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
     * Parses the given input file into a list of directions.
     * 
     * @param inputFilePath path of the input text file, containing a comma-separated list of directions
     * @return list of directions
     * @throws IOException in case the input file could not be read
     */
    static List<HexagonalDirection> parse(Path inputFilePath) throws IOException {
        String inputText = Files.lines(inputFilePath).findFirst().get();
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
                .get();
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
