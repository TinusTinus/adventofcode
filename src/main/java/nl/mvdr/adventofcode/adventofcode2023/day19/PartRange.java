package nl.mvdr.adventofcode.adventofcode2023.day19;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A collection of parts with adjacent property values.
 *
 * @author Martijn van de Rijdt
 */
record PartRange(Map<Property, ValueRange> valueRanges) {
    /**
     * Range of all possible parts.
     */
    static final PartRange FULL_RANGE = new PartRange(
            Stream.of(Property.values())
                .collect(Collectors.toMap(Function.identity(), property -> ValueRange.FULL_RANGE)));
    
    /**
     * @return whether this range is empty
     */
    boolean isEmpty() {
        return valueRanges.values()
                .stream()
                .anyMatch(ValueRange::isEmpty);
    }
    
    /**
     * @return number of values within this range
     */
    long size() {
        return valueRanges.values()
                .stream()
                .mapToLong(ValueRange::size)
                .reduce(Math::multiplyExact)
                .orElseThrow();
    }
}
