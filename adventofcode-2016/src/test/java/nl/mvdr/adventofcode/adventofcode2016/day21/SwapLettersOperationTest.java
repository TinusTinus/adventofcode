package nl.mvdr.adventofcode.adventofcode2016.day21;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class SwapLettersOperationTest {
    
    @Test
    void testParse() {
        var input = "swap letter d with letter b";
        
        var result = SwapLettersOperation.parse(input);
        
        assertTrue(result.isPresent());
        assertEquals('d', result.orElseThrow().x());
        assertEquals('b', result.orElseThrow().y());
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
}
