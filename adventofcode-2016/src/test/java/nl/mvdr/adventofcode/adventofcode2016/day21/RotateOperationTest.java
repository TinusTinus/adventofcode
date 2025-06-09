package nl.mvdr.adventofcode.adventofcode2016.day21;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class RotateOperationTest {
    
    @Test
    void testParse() {
        var input = "rotate left 1 step";
        
        var result = RotateOperation.parse(input);
        
        assertTrue(result.isPresent());
        assertEquals(RotationDirection.LEFT, result.orElseThrow().direction());
        assertEquals(1, result.orElseThrow().x());
    }
    
    @Test
    void testParseInvalid() {
        var input = "herp derp";
        
        var result = RotateOperation.parse(input);
        
        assertFalse(result.isPresent());
    }
    
    @Test
    void testApply() {
        var operation = new RotateOperation(RotationDirection.LEFT, 1);
        
        var result = operation.apply("abcde");
        
        assertEquals("bcdea", result);
    }
}
