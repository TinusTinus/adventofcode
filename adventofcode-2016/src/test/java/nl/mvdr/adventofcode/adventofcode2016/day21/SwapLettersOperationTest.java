package nl.mvdr.adventofcode.adventofcode2016.day21;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

class SwapLettersOperationTest {
    
    @Test
    void testParse() {
        var input = "swap letter d with letter b";
        
        var result = SwapLettersOperation.parse(input);
        
        var operation = (SwapLettersOperation)result.orElseThrow();
        assertEquals('d', operation.x());
        assertEquals('b', operation.y());
    }
    
    @Test
    void testParseInvalid() {
        var input = "herp derp";
        
        var result = SwapLettersOperation.parse(input);
        
        assertFalse(result.isPresent());
    }
    
    @Test
    void testApply() {
        var operation = new SwapLettersOperation('d', 'b');
        
        var result = operation.apply("ebcda");
        
        assertEquals("edcba", result);
    }
    
    @Test
    void testReverse() {
        var operation = new SwapLettersOperation('d', 'b').reverse();
        
        var result = operation.apply("edcba");
        
        assertEquals("ebcda", result);
    }
}
