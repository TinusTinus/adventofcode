package nl.mvdr.adventofcode.adventofcode2016.day21;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

class RotateOperationTest {
    
    @Test
    void testParse() {
        var input = "rotate left 1 step";
        
        var result = RotateOperation.parse(input);
        
        var operation = (RotateOperation)result.orElseThrow();
        assertEquals(RotationDirection.LEFT, operation.direction());
        assertEquals(1, operation.x());
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
        
        var result = operation.apply("abcde").findFirst().orElseThrow();
        
        assertEquals("bcdea", result);
    }
    
    @Test
    void testReverse() {
        var operation = new RotateOperation(RotationDirection.LEFT, 1).reverse();
        
        var result = operation.apply("bcdea").findFirst().orElseThrow();
        
        assertEquals("abcde", result);
    }
}
