package nl.mvdr.adventofcode.adventofcode2023.day09;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * A sequence of values.
 *
 * @author Martijn van de Rijdt
 */
record Sequence(List<Integer> values) {
    /**
     * Parses the textual representation of a history of a value.
     * 
     * @param text textual representation; for example: "0 3 6 9 12 15"
     * @return the sequence represented by the given text
     */
    static Sequence parse(String text) {
        var parts = text.split(" ");
        var values = Stream.of(parts)
                .map(Integer::valueOf)
                .toList();
        return new Sequence(values);
    }

    /**
     * @return next value in this sequence
     */
    int extrapolateNextValue() {
        int result;
        if (values.stream().allMatch(value -> value.intValue() == 0)) {
            result = 0;
        } else {
            var differences = computeDifferences();
            var nextExtrapolatedDifference = differences.extrapolateNextValue();
            result = Math.addExact(values.getLast().intValue(), nextExtrapolatedDifference);
        }
        return result;
    }
    
    /**
     * @return previous value in this sequence
     */
    int extrapolatePreviousValue() {
        int result;
        if (values.stream().allMatch(value -> value.intValue() == 0)) {
            result = 0;
        } else {
            var differences = computeDifferences();
            var previousExtrapolatedDifference = differences.extrapolatePreviousValue();
            result = Math.subtractExact(values.getFirst().intValue(), previousExtrapolatedDifference);
        }
        return result;
    }
    
    /**
     * @return sequence containing the differences between the values of this sequence
     */
    private Sequence computeDifferences() {
        var differences = IntStream.range(0, values.size() - 1)
                .map(i -> Math.subtractExact(values.get(i + 1).intValue(), values.get(i).intValue()))
                .boxed()
                .toList();
        return new Sequence(differences);
    }
}
