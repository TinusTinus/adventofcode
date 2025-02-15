package nl.mvdr.adventofcode.adventofcode2022.day06;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import nl.mvdr.adventofcode.solver.LongSolver;

/**
 * Solution to <a href="https://adventofcode.com/2022/day/06">Tuning Trouble</a>.
 *
 * @author Martijn van de Rijdt
 */
class TuningTrouble implements LongSolver {

    private int sequenceLength;

    /**
     * Constructor.
     * 
     * @param sequenceLength length of the sequence of distinct characters to look for
     */
    TuningTrouble(int sequenceLength) {
        super();
        this.sequenceLength = sequenceLength;
    }
    
    @Override
    public long solve(Stream<String> lines) {
        var input = lines.findFirst().orElseThrow();
        return IntStream.range(sequenceLength, input.length())
                .filter(i -> charactersAllDistinct(input.substring(i - sequenceLength, i)))
                .findFirst()
                .orElseThrow();
    }
    
    /**
     * Checks whether all characters in the given string are distinct.
     * 
     * @param string string to check
     * @return whether all characters are distinct
     */
    private boolean charactersAllDistinct(String string) {
        long distinctCharacterCount = string.chars()
                .distinct()
                .count();
        return distinctCharacterCount == string.length();
    }
}
 