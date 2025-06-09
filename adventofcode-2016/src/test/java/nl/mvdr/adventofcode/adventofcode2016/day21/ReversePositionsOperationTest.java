package nl.mvdr.adventofcode.adventofcode2016.day21;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

class ReversePositionsOperationTest {
    
    @Test
    void testParse() {
        var input = "reverse positions 0 through 4";
        
        var result = ReversePositionsOperation.parse(input);
        
        var operation = (ReversePositionsOperation)result.orElseThrow();
        assertEquals(0, operation.x());
        assertEquals(4, operation.y());
    }
    
    @Test
    void testParseInvalid() {
        var input = "herp derp";
        
        var result = ReversePositionsOperation.parse(input);
        
        assertFalse(result.isPresent());
    }
    
    @Test
    void testApply() {
        var operation = new ReversePositionsOperation(0, 4);
        
        var result = operation.apply("edcba").findFirst().orElseThrow();
        
        assertEquals("abcde", result);
    }
    
    @Test
    void testReverse() {
        var operation = new ReversePositionsOperation(0, 4).reverse();
        
        var result = operation.apply("abcde").findFirst().orElseThrow();
        
        assertEquals("edcba", result);
    }
}
