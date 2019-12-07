package nl.mvdr.adventofcode.adventofcode2019.day07;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Helper class for generating sequences of phase settings.
 *
 * @author Martijn van de Rijdt
 */
class PhaseSettingSequences {

    private PhaseSettingSequences() {
        // private constructor to prevent utility class instantiation
    }
    
    /**
     * Generates all possible sequences of phase settings within the given bounds.
     * 
     * @param lowerBound the lower bound phase setting, inclusive
     * @param upperBound the upper bound phase setting, exclusive
     * @return all possible phase setting sequences
     */
    static Set<List<Integer>> generatePhaseSettingSequences(int lowerBound, int upperBound) {
        Set<Integer> phaseSettings = IntStream.range(lowerBound, upperBound)
                .boxed()
                .collect(Collectors.toSet());
        
        return generateAllPermutations(phaseSettings);
    }
    
    /**
     * Generates all permutations of the given set of values.
     * 
     * @param <T> value type
     * @param values values
     * @return set containing all possible permutations of the given values
     */
    private static <T> Set<List<T>> generateAllPermutations(Set<T> values) {
        Set<List<T>> result;
        
        if (values.isEmpty()) {
            result = Set.of();
        } else if (values.size() == 1) {
            T value = values.iterator().next();
            result = Set.of(List.of(value));
        } else {
            result = new HashSet<>();
            for (T value : values) {
                Set<T> remaining = new HashSet<>(values);
                remaining.remove(value);
                // Recursively determine all permutations of the remaining values
                Set<List<T>> permutations = generateAllPermutations(remaining);
                for (List<T> permutation : permutations) {
                    List<T> resultPermutation = new ArrayList<>();
                    resultPermutation.add(value);
                    resultPermutation.addAll(permutation);
                    result.add(resultPermutation);
                }
            }
        }
        return result;
    }
    
}
