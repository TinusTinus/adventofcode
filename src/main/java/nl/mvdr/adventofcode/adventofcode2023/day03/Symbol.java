package nl.mvdr.adventofcode.adventofcode2023.day03;

import nl.mvdr.adventofcode.point.Point;

/**
 * A symbol in the engine schematic.
 *
 * @param value the symbol value ('*', '#' etc.)
 * @param location location in the schematic
 * @author Martijn van de Rijdt
 */
record Symbol(char value, Point location) {
    
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
}
