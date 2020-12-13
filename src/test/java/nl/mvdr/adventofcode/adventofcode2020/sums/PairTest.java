package nl.mvdr.adventofcode.adventofcode2020.sums;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link Pair}
 *
 * @author Martijn van de Rijdt
 */
public class PairTest {
    /** Test case for {@link Pair#findContiguousSet(long[], long)}. */
    @Test
    public void testFindContiguousTest() {
        long[] numbers = {35L,
                          20L,
                          15L,
                          25L,
                          47L,
                          40L,
                          62L,
                          55L,
                          65L,
                          95L,
                          102L,
                          117L,
                          150L,
                          182L,
                          127L,
                          219L,
                          299L,
                          277L,
                          309L,
                          576L};
        long targetSum = 127L;
        
        Pair pair = Pair.findContiguousSet(numbers, targetSum);
        
        Assertions.assertEquals(15, pair.lhs());
        Assertions.assertEquals(47, pair.rhs());
        Assertions.assertEquals(62, pair.sum());
    }
}
