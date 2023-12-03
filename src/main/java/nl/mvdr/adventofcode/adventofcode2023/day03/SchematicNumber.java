package nl.mvdr.adventofcode.adventofcode2023.day03;

import java.util.Set;

import nl.mvdr.adventofcode.point.Point;

/**
 * A number on the engine schematic.
 *
 * @param value number value
 * @param location part of the engine schematic occupied by the number
 * @author Martijn van de Rijdt
 */
record SchematicNumber(int value, Set<Point> location) {
    
    /**
     * Checks whether this number is adjacent to the given symbol.
     * 
     * @param symbol symbol
     * @return whether this number is adjacent to the given symbol
     */
    private boolean isAdjacent(Symbol symbol) {
        return symbol.isAdjacent(this);
    }
    
    /**
     * Checks whether this is a part number.
     * 
     * @param symbols all symbols on the schematic
     * @return whether this number is a part number
     */
    boolean isPartNumber(Set<Symbol> symbols) {
        return symbols.stream().anyMatch(this::isAdjacent);
    }
}
