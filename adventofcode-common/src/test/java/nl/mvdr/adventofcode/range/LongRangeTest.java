package nl.mvdr.adventofcode.range;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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
        
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(new LongRange(0, 20), result.get(0));
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
        
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(new LongRange(0, 20), result.get(0));
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
        
        Assertions.assertEquals(2, result.size());
        Assertions.assertTrue(result.contains(new LongRange(0, 13)));
        Assertions.assertTrue(result.contains(new LongRange(15, 20)));
    }
}
