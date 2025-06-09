package nl.mvdr.adventofcode.adventofcode2016.day21;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

class SwapPositionsOperationTest {
    
    @Test
    void testParse() {
        var input = "swap position 4 with position 0";
        
        var result = SwapPositionsOperation.parse(input);
        
        var operation = (SwapPositionsOperation)result.orElseThrow();
        assertEquals(4, operation.x());
        assertEquals(0, operation.y());
    }
    
    @Test
    void testParseInvalid() {
        var input = "herp derp";
        
        var result = SwapPositionsOperation.parse(input);
        
        assertFalse(result.isPresent());
    }
    
    @Test
    void testApply() {
        var operation = new SwapPositionsOperation(4, 0);
        
        var result = operation.apply("abcde");
        
        assertEquals("ebcda", result);
    }
}
