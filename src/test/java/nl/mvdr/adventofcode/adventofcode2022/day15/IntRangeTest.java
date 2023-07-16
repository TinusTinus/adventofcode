package nl.mvdr.adventofcode.adventofcode2022.day15;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Unit test cases for {@link IntRange}.
 *
 * @author Martijn van de Rijdt
 */
public class IntRangeTest {
    
    /**
     * Test case for {@link IntRange#reduce(java.util.List)}.
     */
    @Test
    public void testReduceOne() {
        List<IntRange> ranges = List.of(
                new IntRange(0, 7),
                new IntRange(1, 15),
                new IntRange(13, 13),
                new IntRange(13, 19),
                new IntRange(17, 20)
                );
        
        var result = IntRange.reduce(ranges);
        
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(new IntRange(0, 20), result.get(0));
    }
    
    /**
     * Test case for {@link IntRange#reduce(java.util.List)}.
     */
    @Test
    public void testReduceTwo() {
        List<IntRange> ranges = List.of(
                new IntRange(0, 3),
                new IntRange(2, 2),
                new IntRange(3, 13),
                new IntRange(11, 13),
                new IntRange(15, 17),
                new IntRange(15, 20)
                );
        
        var result = IntRange.reduce(ranges);
        
        Assertions.assertEquals(2, result.size());
        Assertions.assertTrue(result.contains(new IntRange(0, 13)));
        Assertions.assertTrue(result.contains(new IntRange(15, 20)));
    }
}
