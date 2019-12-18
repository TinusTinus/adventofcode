package nl.mvdr.adventofcode.permutations;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Helper class to generate permutations of values.
 *
 * @author Martijn van de Rijdt
 */
public class Permutations {
    private Permutations() {
        // Private constructor to prevent utility class instantiation
    }
    
    /**
     * Generates all permutations of the given set of values.
     * 
     * @param <T> value type
     * @param values values
     * @return set containing all possible permutations of the given values
     */
    public static <T> Set<List<T>> generateAllPermutations(Set<T> values) {
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
                    List<T> resultPermutation = new ArrayList<>(permutation.size() + 1);
                    resultPermutation.add(value);
                    resultPermutation.addAll(permutation);
                    result.add(resultPermutation);
                }
            }
        }
        return result;
    }
}
