package nl.mvdr.adventofcode.adventofcode2022.day25;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test class for {@link SnafuNumber}.
 *
 * @author Martijn van de Rijdt
 */
public class SnafuNumberTest {
    
    /**
     * Tests input parsing.
     */
    @Test
    public void testParse() {
        var input = "1=-0-2";
        
        var result = SnafuNumber.parse(input);
        
        var expected = new SnafuNumber(List.of(SnafuDigit.ONE, SnafuDigit.DOUBLE_MINUS, SnafuDigit.MINUS, SnafuDigit.ZERO, SnafuDigit.MINUS, SnafuDigit.TWO));
        Assertions.assertEquals(expected, result);
    }
    
    /**
     * Tests {@link SnafuNumber#toString()}.
     */
    @Test
    public void testToString() {
        var number = new SnafuNumber(List.of(SnafuDigit.ONE, SnafuDigit.DOUBLE_MINUS, SnafuDigit.MINUS, SnafuDigit.ZERO, SnafuDigit.MINUS, SnafuDigit.TWO));
        
        var result = number.toString();
        
        Assertions.assertEquals("1=-0-2", result);
    }
}
