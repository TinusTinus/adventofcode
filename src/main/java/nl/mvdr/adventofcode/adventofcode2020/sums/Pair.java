package nl.mvdr.adventofcode.adventofcode2020.sums;

import java.util.Optional;
import java.util.function.Function;
import java.util.stream.IntStream;

/**
 * A pair of integers.
 *
 * @param lhs the first integer (left-hand side)
 * @param rhs the second integer (right-hand side)
 * 
 * @author Martijn van de Rijdt
 */
public record Pair(int lhs, int rhs) {

    /**
     * Out of the given integers, this method finds the pair of integers that sum to the given number.
     * 
     * @param integers integers
     * @param sum target sum
     * @return pair which sums to the target
     */
    public static Optional<Pair> findPairWhichSumsTo(int[] integers, int sum) {
        return IntStream.range(0, integers.length)
                .mapToObj(i -> IntStream.range(i + 1, integers.length).mapToObj(j -> new Pair(integers[i], integers[j])))
                .flatMap(Function.identity())
                .filter(pair -> pair.sum() == sum)
                .findFirst();
    }
    
    
    /** @return the result of adding the two integers together */
    public int sum() {
        return lhs + rhs;
    }
    
    /** @return the result of multiplying the two integers with each other */
    public int product() {
        return lhs * rhs;
    }
}
