package nl.mvdr.adventofcode.adventofcode2023.day05;

import java.util.List;

/**
 * A map which describes how to convert numbers from a source category into numbers in a destination category.
 *
 * @author Martijn van de Rijdt
 */
record ConversionMap(List<ConversionMapRange> ranges) {
    /**
     * Maps the given source number to a destination number.
     * 
     * @param sourceNumber source number (for example, if this is a seed-to-soil map, a seed number)
     * @return destination number (in the above example: a soil number)
     */
    long map(long sourceNumber) {
        return ranges.stream()
            .filter(range -> range.contains(sourceNumber))
            .mapToLong(range -> range.map(sourceNumber))
            .findFirst()
            .orElse(sourceNumber);
    }
}
