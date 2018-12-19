package nl.mvdr.adventofcode.adventofcode2018.day18;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * A lumber collection area.
 *
 * @author Martijn van de Rijdt
 */
class LumberCollectionArea {
    
    /** List of rows of acres. */
    private final List<List<AcreType>> acres;
    
    /**
     * Constructor.
     * 
     * @param acres list of rows of acres
     */
    private LumberCollectionArea(List<List<AcreType>> acres) {
        super();
        
        this.acres = acres;
    }
    
    /**
     * Parses an input text file to a lumber collection area.
     * 
     * @param line line of characters representing acre types
     * @return lumber collection area
     */
    static LumberCollectionArea parse(Path path) throws IOException {
        List<List<AcreType>> acres = Files.lines(path)
                // ignore empty lines (the last line in the file)
                .filter(Objects::nonNull)
                .filter(line -> !line.isBlank())
                .map(LumberCollectionArea::parseLine)
                .collect(Collectors.toList());
        
        return new LumberCollectionArea(acres);
    }
    
    /**
     * Parses a single line from an input text file to a row of acres.
     * 
     * @param line line of characters representing acre types
     * @return row of acres
     */
    private static List<AcreType> parseLine(String line) {
        return line.chars()
                .mapToObj(character -> AcreType.fromCharacter((char) character))
                .collect(Collectors.toList());
    }
    
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        
        acres.forEach(row -> {
            append(builder, row);
            builder.append("\n");
        });
        
        return builder.toString();
    }
    
    private void append(StringBuilder builder, List<AcreType> row) {
        row.stream()
                .map(AcreType::toString)
                .forEach(builder::append);
    }
}
