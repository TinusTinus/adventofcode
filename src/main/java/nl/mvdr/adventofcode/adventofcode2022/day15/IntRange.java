package nl.mvdr.adventofcode.adventofcode2022.day15;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A range of integers.
 *
 * @param min minimum value
 * @param max maximum value
 * @author Martijn van de Rijdt
 */
record IntRange(int min, int max) implements Comparable<IntRange> {

    private static final Logger LOGGER = LoggerFactory.getLogger(IntRange.class);
    
    /**
     * Merges the given ranges where possible.
     * 
     * @param ranges int ranges
     * @return merged int ranges
     */
    static List<IntRange> reduce(List<IntRange> ranges) { 
        List<IntRange> result = new ArrayList<>(ranges);
        
        boolean done = false;
        while (!done) {
            // Find two overlapping ranges
            IntRange overlapping0 = null;
            IntRange overlapping1 = null;
            for (int i = 0; overlapping0 == null && i != result.size(); i++) {
                for (int j = i + 1; overlapping0 == null && j != result.size(); j++) {
                    if (result.get(i).overlaps(result.get(j))) {
                        overlapping0 = result.get(i);
                        overlapping1 = result.get(j);
                    }
                }
            }

            if (overlapping0 == null) {
                done = true;
            } else {
                result.remove(overlapping0);
                result.remove(overlapping1);
                var merged = overlapping0.merge(overlapping1);
                LOGGER.debug("Merged {} and {} to {}", overlapping0, overlapping1, merged);
                result.add(merged);
                LOGGER.debug("Ranges: {}", result);
            }
        }
        
        Collections.sort(result);
        
        return result;
    }
    
    /**
     * @return stream for this int range
     */
    IntStream stream() {
        return IntStream.range(min, max + 1);
    }
    
    /**
     * @return whether this range is empty
     */
    boolean isEmpty() {
        return max < min;
    }
    
    /**
     * Whether this range overlaps with the given other range.
     * 
     * @param other other range
     * @return whether there is any overlap
     */
    boolean overlaps(IntRange other) {
        return this.min <= other.max && other.min <= this.max;
    }
    
    /**
     * Produces a new int range which covers both this and the given int range.
     * 
     * @param other other int range
     * @return range representing all values that are part of these two ranges
     */
    IntRange merge(IntRange other) {
        if (!overlaps(other)) {
            throw new IllegalArgumentException("Ranges do not overlap: " + this + ", " + other);
        }
        return new IntRange(Math.min(this.min, other.min), Math.max(this.max, other.max));
    }
    
    @Override
    public int compareTo(IntRange other) {
        return Comparator.comparing(IntRange::min)
                .thenComparing(IntRange::max)
                .compare(this, other);
    }
}
