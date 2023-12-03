package nl.mvdr.adventofcode.adventofcode2023.day03;

import java.util.Set;
import java.util.stream.Collectors;

import nl.mvdr.adventofcode.point.Point;

/**
 * A symbol in the engine schematic.
 *
 * @param value the symbol value ('*', '#' etc.)
 * @param location location in the schematic
 * @author Martijn van de Rijdt
 */
record Symbol(char value, Point location) {
    
    private static final char GEAR_SYMBOL = '*';
    
    /**
     * Checks whether this symbol is adjacent to the given number.
     * 
     * @param number number
     * @return whether this symbol is adjacent to the given number
     */
    boolean isAdjacent(SchematicNumber number) {
        var neighbours = location.neighboursIncludingDiagonals();
        return number.location()
                .stream()
                .anyMatch(neighbours::contains);
    }
    
    /**
     * @return the gear ratio of this symbol; 0 if this is in fact not a gear
     */
    int gearRatio(Set<SchematicNumber> numbers) {
        int result;
        if (value == GEAR_SYMBOL) {
            var adjacentNumbers = numbers.stream()
                    .filter(this::isAdjacent)
                    .collect(Collectors.toSet());
            if (adjacentNumbers.size() == 2) {
                result = adjacentNumbers.stream()
                        .mapToInt(SchematicNumber::value)
                        .reduce(1, (i, j) -> i * j);
            } else {
                // No two adjacent numbers: not a gear
                result = 0;
            }
        } else {
            // Different symbol: not a gear
            result = 0;
        }
        return result;
    }
}
