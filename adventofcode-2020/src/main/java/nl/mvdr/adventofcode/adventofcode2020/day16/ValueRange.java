package nl.mvdr.adventofcode.adventofcode2020.day16;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Range of valid values for a field of a train ticket.
 *
 * @author Martijn van de Rijdt
 */
record ValueRange(int minimum, int maximum) {

    /**
     * Parses a textual representation of a list of value ranges from the puzzle input.
     * 
     * @param text textual representation of a number of value ranges, for example: {@code 1-3 or 5-7}
     * @return list of value ranges
     */
    static List<ValueRange> parse(String text) {
        return Stream.of(text.split(" or "))
                .map(ValueRange::parseRange)
                .collect(Collectors.toList());
    }
    
    /**
     * Parses the textual representation of a value range from the puzzle input.
     * 
     * @param text textual representation of a value range, for example: {@code 1-3}
     * @return value range
     */
    private static ValueRange parseRange(String text) {
        String[] parts = text.split("-");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Invalid value range: " + text);
        }
        int minimum = Integer.parseInt(parts[0]);
        int maximum = Integer.parseInt(parts[1]);
        return new ValueRange(minimum, maximum);
    }
    
    /**
     * Determines whether the given value lies within this range.
     * 
     * @param value value
     * @return whether the given value is in range
     */
    boolean contains(int value) {
        // Bounds are inclusive.
        return minimum <= value && value <= maximum;
    }
}
