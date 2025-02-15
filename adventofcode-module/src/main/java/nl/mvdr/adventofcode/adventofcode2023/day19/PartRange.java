package nl.mvdr.adventofcode.adventofcode2023.day19;

import java.util.EnumMap;
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
     * An empty range.
     * 
     * Note that this is not necessarily unique.
     * Do not use {@code EMPTY_RANGE.equals} to check whether a range is empty;
     * use {@link #isEmpty()} instead.
     */
    static final PartRange EMPTY_RANGE = new PartRange(
            Stream.of(Property.values())
            .collect(Collectors.toMap(Function.identity(), property -> ValueRange.EMPTY_RANGE)));
    
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
    
    /**
     * Returns a copy of this range where the given value has been replaced.
     * 
     * @param property key
     * @param valueRange new value range for the given property
     * @return part range
     */
    PartRange withValueRange(Property property, ValueRange valueRange) {
        Map<Property, ValueRange> newValueRanges = new EnumMap<>(valueRanges);
        newValueRanges.put(property, valueRange);
        return new PartRange(newValueRanges);
    }
}
