package nl.mvdr.adventofcode.adventofcode2018.day18;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
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
    
    /**
     * Gets the value at the given coordinates.
     * 
     * @param x x coordinate; does not need to be in bounds
     * @param y y coordinate; does not need to be in bounds
     * @return value at the given coordinates, or empty if out of bounds
     */
    private Optional<AcreType> getValueAt(int x, int y) {
        Optional<AcreType> result;
        
        if (0 <= x && x < acres.size()) {
            List<AcreType> row = acres.get(x);
            if (0 <= y && y < row.size()) {
                result = Optional.of(row.get(y));
            } else {
                // y out of bounds
                result = Optional.empty();
            }
        } else {
            // x out of bounds
            result = Optional.empty();
        }
        
        return result;
    }
    
    private List<AcreType> getNeighbours(int x, int y) {
        List<Optional<AcreType>> neighbours = new ArrayList<>();
        neighbours.add(getValueAt(x - 1, y - 1));
        neighbours.add(getValueAt(x - 1, y   ));
        neighbours.add(getValueAt(x - 1, y + 1));
        neighbours.add(getValueAt(x    , y - 1));
        neighbours.add(getValueAt(x    , y + 1));
        neighbours.add(getValueAt(x + 1, y - 1));
        neighbours.add(getValueAt(x + 1, y    ));
        neighbours.add(getValueAt(x + 1, y + 1));
        
        return neighbours.stream()
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }
    
    /**
     * Computes the state of the lumber collection area, after the given number of minutes has passed.
     *
     * @param minutes number of minutes; must be at least 0
     * @return new state of the lumber collection area
     */
    LumberCollectionArea tick(int minutes) {
        LumberCollectionArea result;
        if (minutes < 0) {
            throw new IllegalArgumentException("Minutes may not be negative, was: " + minutes);
        } else {
            result = this;
            for (int i = 0; i != minutes; i++) {
                result = result.tick();
            }
        }
        return result;
    }
    
    /**
     * Computes the next state of the lumber collection area, after a single minute has passed.
     * 
     * @return new state of the lumber collection area
     */
    private LumberCollectionArea tick() {
        List<List<AcreType>> nextAcres = new ArrayList<>();
        
        for (int x = 0; x != acres.size(); x++) {
            List<AcreType> nextRow = new ArrayList<>();
            
            for (int y = 0; y != acres.get(x).size(); y++) {
                AcreType acreType = acres.get(x).get(y);
                List<AcreType> neighbours = getNeighbours(x, y);
                
                AcreType nextAcreType;
                if (acreType == AcreType.OPEN_GROUND) {
                    // An open acre will become filled with trees if three or more adjacent
                    // acres contained trees. Otherwise, nothing happens.
                    if (3 <= neighbours.stream().filter(neighbour -> neighbour == AcreType.TREES).count()) {
                        nextAcreType = AcreType.TREES;
                    } else {
                        nextAcreType = acreType;
                    }
                } else if (acreType == AcreType.TREES) {
                    // An acre filled with trees will become a lumberyard if three or more
                    // adjacent acres were lumberyards. Otherwise, nothing happens.
                    if (3 <= neighbours.stream().filter(neighbour -> neighbour == AcreType.LUMBERYARD).count()) {
                        nextAcreType = AcreType.LUMBERYARD;
                    } else {
                        nextAcreType = acreType;
                    }
                } else if (acreType == AcreType.LUMBERYARD) {
                    // An acre containing a lumberyard will remain a lumberyard if it was
                    // adjacent to at least one other lumberyard and at least one acre
                    // containing trees. Otherwise, it becomes open.
                    if (neighbours.contains(AcreType.LUMBERYARD) && neighbours.contains(AcreType.TREES)) {
                        nextAcreType = AcreType.LUMBERYARD;
                    } else {
                        nextAcreType = AcreType.OPEN_GROUND;
                    }
                    
                } else {
                    throw new IllegalStateException("Unexpected acre type: " + acreType);
                }
                nextRow.add(nextAcreType);
            }
            
            nextAcres.add(nextRow);
        }
        
        return new LumberCollectionArea(nextAcres);
    }
    
    // getter for unit tests
    List<List<AcreType>> getAcres() {
        return acres;
    }
    
    /**
     * Computes the resource value of this lumber collection area.
     * 
     * Multiplying the number of wooded acres by the number of lumberyards gives the total resource value.
     * 
     * @return the resource value of this lumber collection area
     */
    long computeResourceValue() {
        Map<AcreType, Long> acreTypeCounts = acres.stream()
                .flatMap(List::stream)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        
        Long woodedAcres = acreTypeCounts.getOrDefault(AcreType.TREES, Long.valueOf(0L));
        Long lumberyards = acreTypeCounts.getOrDefault(AcreType.LUMBERYARD, Long.valueOf(0L));
                
        return woodedAcres.longValue() * lumberyards.longValue();
    }
}
