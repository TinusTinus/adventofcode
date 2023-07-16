package nl.mvdr.adventofcode.adventofcode2022.day15;

import java.util.Comparator;
import java.util.stream.IntStream;

/**
 * A range of integers.
 *
 * @param min minimum value
 * @param max maximum value
 * @author Martijn van de Rijdt
 */
record IntRange(int min, int max) implements Comparable<IntRange> {

    /**
     * @return stream for this int range
     */
    IntStream stream() {
        return IntStream.range(min, max + 1);
    }
    
    /**
     * Whether this range overlaps with the given other range.
     * 
     * @param other other range
     * @return whether there is any overlap
     */
    boolean overlaps(IntRange other) {
        return this.min <= other.max || other.min <= this.max;
    }
    
    @Override
    public int compareTo(IntRange other) {
        return Comparator.comparing(IntRange::min)
                .thenComparing(IntRange::max)
                .compare(this, other);
    }
}
