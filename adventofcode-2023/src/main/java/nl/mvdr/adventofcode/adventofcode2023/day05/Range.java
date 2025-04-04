package nl.mvdr.adventofcode.adventofcode2023.day05;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * A range of numbers.
 *
 * @param start start of the range
 * @param length length of the range
 * @author Martijn van de Rijdt
 */
record Range(long start, long length) {

    private static final String SEEDS_PREFIX = "seeds: ";

    /**
     * Parses a line containing a list of seeds.
     * 
     * @param text first line of the puzzle input, for example: "seeds: 79 14 55 13"
     * @param individualSeeds whether seeds should be interpreted as individual values (as in part 1) rather than as ranges (as in part 2)
     * @return seed ranges
     */
    static List<Range> parseSeeds(String text, boolean individualSeeds) {
        if (!text.startsWith(SEEDS_PREFIX)) {
            throw new IllegalArgumentException("Unable to parse as seeds: " + text);
        }
        var parts = text.substring(SEEDS_PREFIX.length()).split(" ");
        
        List<Range> result;
        if (individualSeeds) {
            // Interpret each value as an individual seed value
            result = Stream.of(parts)
                    .map(Long::valueOf)
                    .mapToLong(Long::longValue)
                    // Convert each value to a range of length 1
                    .mapToObj(seedNumber -> new Range(seedNumber, 1L))
                    .toList();
        } else {
            // Interpret pairs of values as a range specification
            if (parts.length % 2 != 0) {
                throw new IllegalArgumentException("Unable to parse as seed ranges: " + text);
            }
            result = IntStream.range(0, parts.length)
                    .filter(i -> i % 2 == 0)
                    .mapToObj(i -> new Range(Long.parseLong(parts[i]), Long.parseLong(parts[i + 1])))
                    .toList();
        }
        return result;
    }
    
    /**
     * Creates a new range.
     * 
     * @param startInclusive start of the range, inclusive
     * @param endExclusive end of the range, exclusive; if endExclusive <= startInclusive, the resulting range will be empty
     * @return range
     */
    private static Range of(long startInclusive, long endExclusive) {
        long resultLength = Math.max(0, Math.subtractExact(endExclusive, startInclusive));
        return new Range(startInclusive, resultLength);
    }
    
    /**
     * Constructor.
     * 
     * @param start start of the range
     * @param length length of the range
     */
    Range(long start, long length) {
        if (length < 0) {
            throw new IllegalArgumentException("Invalid length: " + length);
        }
        this.start = start;
        this.length = length;
    }
    
    /**
     * Creates a copy of the given list of ranges, with all empty ranges removed.
     * 
     * @param ranges list of ranges
     * @return filtered list
     */
    static List<Range> removeEmptyRanges(List<Range> ranges) {
        return ranges.stream()
                .filter(Range::nonEmpty)
                .toList();
    }

    /**
     * @return end of the range (exclusive)
     */
    private long endExclusive() {
        return Math.addExact(start, length);
    }
    
    /**
     * Checks whether the entirety of the given other range is contained within this one.
     * 
     * @param other other range
     * @return whether the given other range is entirely contained within this one
     */
    boolean contains(Range other) {
        return other.isEmpty() ||
                (start <= other.start() && other.endExclusive() <= endExclusive());
    }
    
    /**
     * @return whether this range is empty
     */
    boolean isEmpty() {
        return length == 0;
    }
    
    /**
     * @return whether this range is non-empty
     */
    boolean nonEmpty() {
        return !isEmpty();
    }
    
    /**
     * Determines which part of the given other range overlaps with this one, and returns it.
     * 
     * @param other other range
     * @return overlapping part; note that this may be empty!
     */
    Range overlappingPart(Range other) {
        var resultStart = Math.max(start, other.start());
        var resultEnd = Math.min(endExclusive(), other.endExclusive());
        return of(resultStart, resultEnd);
    }
    
    /**
     * Determines the part of the given range which preceeds this range.
     * 
     * @param other other range
     * @return preceding part
     */
    Range precedingPart(Range other) {
        long resultStart = other.start();
        long resultEnd = Math.min(other.endExclusive(), start);
        return of(resultStart, resultEnd);
    }
    
    /**
     * Determines the part of the given range which follows this range.
     * 
     * @param other other range
     * @return following part
     */
    Range followingPart(Range other) {
        long resultStart = Math.max(endExclusive(), other.start());
        long resultEnd = other.endExclusive();
        return of(resultStart, resultEnd);
    }

}
