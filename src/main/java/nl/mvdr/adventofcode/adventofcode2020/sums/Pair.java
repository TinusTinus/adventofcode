package nl.mvdr.adventofcode.adventofcode2020.sums;

import java.util.Optional;
import java.util.function.Function;
import java.util.stream.IntStream;

/**
 * A pair of numbers.
 *
 * @param lhs the first number (left-hand side)
 * @param rhs the second number (right-hand side)
 * 
 * @author Martijn van de Rijdt
 */
public record Pair(long lhs, long rhs) {

    /**
     * Out of the given numbers, this method finds the pair of numbers that sum to the given number.
     * 
     * @param numbers numbers
     * @param sum target sum
     * @return pair which sums to the target
     */
    public static Optional<Pair> findPairWhichSumsTo(long[] numbers, long sum) {
        return IntStream.range(0, numbers.length)
                .mapToObj(i -> IntStream.range(i + 1, numbers.length).mapToObj(j -> new Pair(numbers[i], numbers[j])))
                .flatMap(Function.identity())
                .filter(pair -> pair.sum() == sum)
                .findFirst();
    }
    
    
    /** @return the result of adding the two numbers together */
    public long sum() {
        return lhs + rhs;
    }
    
    /** @return the result of multiplying the two numbers with each other */
    public long product() {
        return lhs * rhs;
    }
}
