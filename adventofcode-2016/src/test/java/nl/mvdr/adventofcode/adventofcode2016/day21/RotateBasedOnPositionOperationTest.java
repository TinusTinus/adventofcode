package nl.mvdr.adventofcode.adventofcode2016.day21;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class RotateBasedOnPositionOperationTest {
    
    @ParameterizedTest
    @CsvSource( {
        "rotate based on position of letter b, b",
        "rotate based on position of letter d, d"
    })
    void testParse(String input, char expectedX) {
        var result = RotateBasedOnPositionOperation.parse(input);
        
        var operation = (RotateBasedOnPositionOperation)result.orElseThrow();
        assertEquals(expectedX, operation.x());
    }
    
    @Test
    void testParseInvalid() {
        var input = "herp derp";
        
        var result = RotateBasedOnPositionOperation.parse(input);
        
        assertFalse(result.isPresent());
    }
    
    @ParameterizedTest
    @CsvSource( {
        "b, abdec, ecabd",
        "d, ecabd, decab"
    })
    void testApply(char x, String input, String expectedResult) {
        var operation = new RotateBasedOnPositionOperation(x);
        
        var result = operation.apply(input);
        
        assertEquals(expectedResult, result);
    }
}
