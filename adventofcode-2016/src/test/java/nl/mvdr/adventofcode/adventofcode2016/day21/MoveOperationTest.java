package nl.mvdr.adventofcode.adventofcode2016.day21;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class MoveOperationTest {
    
    @ParameterizedTest
    @CsvSource({
        "move position 1 to position 4, 1, 4",
        "move position 3 to position 0, 3, 0"
    })
    void testParse(String input, int expectedX, int expectedY) {
        var result = MoveOperation.parse(input);
        
        var operation = (MoveOperation)result.orElseThrow();
        assertEquals(expectedX, operation.x());
        assertEquals(expectedY, operation.y());
    }
    
    @Test
    void testParseInvalid() {
        var input = "herp derp";
        
        var result = MoveOperation.parse(input);
        
        assertFalse(result.isPresent());
    }
    
    @ParameterizedTest
    @CsvSource({
        "1, 4, bcdea, bdeac",
        "3, 0, bdeac, abdec"
    })
    void testApply(int x, int y, String input, String expectedOutput) {
        var operation = new MoveOperation(x, y);
        
        var result = operation.apply(input).findFirst().orElseThrow();
        
        assertEquals(expectedOutput, result);
    }
    
    @ParameterizedTest
    @CsvSource({
        "1, 4, bdeac, bcdea",
        "3, 0, abdec, bdeac"
    })
    void testReverse(int x, int y, String input, String expectedOutput) {
        var operation = new MoveOperation(x, y).reverse();
        
        var result = operation.apply(input).findFirst().orElseThrow();
        
        assertEquals(expectedOutput, result);
    }
}
