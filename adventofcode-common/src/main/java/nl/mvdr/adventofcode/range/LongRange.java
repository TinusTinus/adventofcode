package nl.mvdr.adventofcode.range;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A range of longs.
 *
 * @param min minimum value (inclusive)
 * @param max maximum value (inclusive)
 * @author Martijn van de Rijdt
 */
public record LongRange(long min, long max) implements Comparable<LongRange> {

    private static final Logger LOGGER = LoggerFactory.getLogger(LongRange.class);
    
    /**
     * Parses the string representation of a range.
     * 
     * @param line string representation; for example: "4-7"
     */
    public static LongRange parse(String line) {
        var parts = line.split("-");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Unable to parse as a long range: " + line);
        }
        var min = Long.parseLong(parts[0]);
        var max = Long.parseLong(parts[1]);
        
        return new LongRange(min, max);
    }
    
    /**
     * Merges the given ranges where possible.
     * 
     * @param ranges nonempty ranges
     * @return merged ranges, sorted
     */
    public static List<LongRange> reduce(List<LongRange> ranges) { 
        List<LongRange> result = new ArrayList<>(ranges);
        
        boolean done = false;
        while (!done) {
            // Find two overlapping ranges
            LongRange overlapping0 = null;
            LongRange overlapping1 = null;
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
     * Whether this range overlaps with the given other range.
     * 
     * Note: this method assumes that the range is not empty.
     * 
     * @param other other range
     * @return whether there is any overlap
     */
    private boolean canBeMerged(LongRange other) {
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
    private LongRange merge(LongRange other) {
        return new LongRange(Math.min(this.min, other.min), Math.max(this.max, other.max));
    }

    public boolean contains(long value) {
        return this.min <= value && value <= this.max;
    }

    public long size() {
        return this.max - this.min + 1;
    }
    
    @Override
    public int compareTo(LongRange other) {
        return Comparator.comparing(LongRange::min)
                .thenComparing(LongRange::max)
                .compare(this, other);
    }
}
