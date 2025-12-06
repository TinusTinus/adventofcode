package nl.mvdr.adventofcode.range;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class LongRangeTest {
    
    @Test
    void testParse() {
        var input = "132-2378";
        
        var result = LongRange.parse(input);
        
        assertEquals(new LongRange(132, 2378), result);
    }
    
    @Test
    void testReduce() {
        var ranges = List.of(
                new LongRange(0, 1),
                new LongRange(0, 4),
                new LongRange(5, 11),
                new LongRange(9, 15),
                new LongRange(13, 15),
                new LongRange(13, 20));
        
        var result = LongRange.reduce(ranges);
        
        assertEquals(1, result.size());
        assertEquals(new LongRange(0, 20), result.getFirst());
    }

    @Test
    void testReduceOne() {
        List<LongRange> ranges = List.of(
                new LongRange(0, 7),
                new LongRange(1, 15),
                new LongRange(13, 13),
                new LongRange(13, 19),
                new LongRange(17, 20));
        
        var result = LongRange.reduce(ranges);
        
        assertEquals(1, result.size());
        assertEquals(new LongRange(0, 20), result.getFirst());
    }
    
    @Test
    void testReduceTwo() {
        List<LongRange> ranges = List.of(
                new LongRange(0, 3),
                new LongRange(2, 2),
                new LongRange(3, 13),
                new LongRange(11, 13),
                new LongRange(15, 17),
                new LongRange(15, 20));
        
        var result = LongRange.reduce(ranges);
        
        assertEquals(2, result.size());
        assertTrue(result.contains(new LongRange(0, 13)));
        assertTrue(result.contains(new LongRange(15, 20)));
    }

    @ParameterizedTest
    @CsvSource({ "3, 6, 3", "3, 6, 4", "3, 6, 5", "3, 6, 6", "-3, 2, 1", "-3, 2, -1", "-3, 2, 0", "-3, -1, -2" })
    void testContains(long min, long max, long value) {
        var range = new LongRange(min, max);

        var result = range.contains(value);

        assertTrue(result);
    }

    @ParameterizedTest
    @CsvSource({ "3, 6, 0", "3, 6, 2", "3, 6, 7", "3, 6, 8", "-3, 2, -4", "-3, 2, 3", "-3, 2, 111111111", "-3, -1, 1" })
    void testDoesNotContain(long min, long max, long value) {
        var range = new LongRange(min, max);

        var result = range.contains(value);

        assertFalse(result);
    }
}
