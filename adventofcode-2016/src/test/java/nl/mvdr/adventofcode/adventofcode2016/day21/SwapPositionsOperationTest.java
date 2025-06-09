package nl.mvdr.adventofcode.adventofcode2016.day21;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class SwapPositionsOperationTest {
    
    @Test
    void testParse() {
        var input = "swap position 4 with position 0";
        
        var result = SwapPositionsOperation.parse(input);
        
        assertTrue(result.isPresent());
        assertEquals(4, result.orElseThrow().x());
        assertEquals(0, result.orElseThrow().y());
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
