package nl.mvdr.adventofcode.adventofcode2022.day15;

import java.util.Comparator;
import java.util.stream.IntStream;

/**
 * A range of integers.
 *
 * @author Martijn van de Rijdt
 */
record IntRange(int startInclusive, int endExclusive) implements Comparable<IntRange> {
    /**
     * @return stream for this int range
     */
    IntStream stream() {
        return IntStream.range(startInclusive, endExclusive);
    }
    
    @Override
    public int compareTo(IntRange other) {
        return Comparator.comparing(IntRange::startInclusive)
                .thenComparing(IntRange::endExclusive)
                .compare(this, other);
    }
}
