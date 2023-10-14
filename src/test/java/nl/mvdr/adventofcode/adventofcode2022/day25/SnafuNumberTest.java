package nl.mvdr.adventofcode.adventofcode2022.day25;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

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

    /**
     * Test case for {@link SnafuNumber#add(SnafuNumber)}.
     * 
     * @param left string representation of the left-hand side
     * @param right string representation of the right-hand side
     * @param sum string representation of the expected sum
     */
    @ParameterizedTest
    @MethodSource
    public void testAdd(String left, String right, String sum) { 
        var leftNumber = SnafuNumber.parse(left);
        var rightNumber = SnafuNumber.parse(right);
        
        var result = leftNumber.add(rightNumber);
        
        var expectedNumber = SnafuNumber.parse(sum);
        Assertions.assertEquals(expectedNumber, result);
    }
    
    /**
     * @return arguments for {@link #testAdd(String, String, String)}
     */
    private static Stream<Arguments> testAdd() {
        return Stream.of(
            Arguments.of("0", "0", "0"),
            Arguments.of("0", "1", "1"),
            Arguments.of("1", "0", "1"),
            Arguments.of("1", "1", "2"),
            Arguments.of("2", "1", "1="),
            Arguments.of("1", "2", "1="),
            Arguments.of("2", "2", "1-"),
            Arguments.of("2", "3", "10"),
            Arguments.of("3", "2", "10")
        );
    }
}
