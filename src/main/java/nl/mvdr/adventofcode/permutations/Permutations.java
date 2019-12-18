package nl.mvdr.adventofcode.permutations;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

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
     * @return stream of all possible permutations of the given values
     */
    public static <T> Stream<List<T>> generateAllPermutations(Set<T> values) {
        Stream<List<T>> result;
        
        if (values.isEmpty()) {
            result = Stream.of();
        } else if (values.size() == 1) {
            T value = values.iterator().next();
            result = Stream.of(List.of(value));
        } else {
            result = values.parallelStream()
                    .flatMap(value -> {
                        Set<T> remaining = new HashSet<>(values);
                        remaining.remove(value);
                        // Recursively determine all permutations of the remaining values
                        return generateAllPermutations(remaining).map(permutation -> {
                            List<T> resultPermutation = new ArrayList<>(permutation.size() + 1);
                            resultPermutation.add(value);
                            resultPermutation.addAll(permutation);
                            return resultPermutation;
                        });
                    });
        }
        return result;
    }
}
