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
     * @param ranges nonempty int ranges
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
                    if (result.get(i).canBeMerged(result.get(j))) {
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
     * @return whether this range is empty
     */
    boolean isEmpty() {
        return max < min;
    }
    
    /**
     * @return number of integers in the range
     */
    int size() {
        int result;
        if (isEmpty()) {
            result = 0;
        } else {
            result = max + 1 - min;
        }
        return result;
    }
    
    /**
     * @return stream for this int range
     */
    IntStream stream() {
        return IntStream.range(min, max + 1);
    }
    
    /**
     * Whether this range overlaps with the given other range.
     * 
     * Note: this method assumes that the range is not empty.
     * 
     * @param other other range
     * @return whether there is any overlap
     */
    private boolean canBeMerged(IntRange other) {
        return this.min <= other.max && other.min <= this.max // overlaps
                || this.min == other.max + 1 || other.min == this.max + 1; // are connected
    }
    
    /**
     * Produces a new int range which covers both this and the given int range.
     * 
     * Note: this method assumes that the range is not empty.
     * 
     * @param other other int range
     * @return range representing all values that are part of these two ranges
     */
    private IntRange merge(IntRange other) {
        return new IntRange(Math.min(this.min, other.min), Math.max(this.max, other.max));
    }
    
    @Override
    public int compareTo(IntRange other) {
        return Comparator.comparing(IntRange::min)
                .thenComparing(IntRange::max)
                .compare(this, other);
    }
}
