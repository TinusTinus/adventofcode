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
    void testParse() {
        var input = "1=-0-2";
        
        var result = SnafuNumber.parse(input);
        
        var expected = new SnafuNumber(List.of(SnafuDigit.ONE, SnafuDigit.DOUBLE_MINUS, SnafuDigit.MINUS, SnafuDigit.ZERO, SnafuDigit.MINUS, SnafuDigit.TWO));
        Assertions.assertEquals(expected, result);
    }
    
    /**
     * Tests {@link SnafuNumber#toString()}.
     */
    @Test
    void testToString() {
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
    void testAdd(String left, String right, String sum) { 
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
            Arguments.of("3", "2", "10"),
            Arguments.of("1=", "1=", "11")
        );
    }
    
    /**
     * Test case for {@link SnafuNumber#intValue()}.
     * 
     * @param number given SNAFU number
     * @param intValue expected int value
     */
    @ParameterizedTest
    @MethodSource
    void testIntValue(String number, int expected) {
        var snafuNumber = SnafuNumber.parse(number);
        
        var result = snafuNumber.intValue();
        
        Assertions.assertEquals(expected, result);
    }
    
    /**
     * Test case for {@link SnafuNumber#intValue()}.
     * 
     * @param number given SNAFU number
     * @param intValue expected int value
     */
    @ParameterizedTest
    @MethodSource("testIntValue")
    void testValueOf(String expected, int i) {
        var result = SnafuNumber.valueOf(i);

        var expectedSnafuNumber = SnafuNumber.parse(expected);
        Assertions.assertEquals(expectedSnafuNumber, result);
    }
    
    /**
     * @return arguments for converting SNAFU numbers to and from integers
     */
    private static Stream<Arguments> testIntValue() {
        return Stream.of(
                Arguments.of("0", Integer.valueOf(0)),
                Arguments.of("1", Integer.valueOf(1)),
                Arguments.of("2", Integer.valueOf(2)),
                Arguments.of("1=", Integer.valueOf(3)),
                Arguments.of("1-", Integer.valueOf(4)),
                Arguments.of("10", Integer.valueOf(5)),
                Arguments.of("11", Integer.valueOf(6)),
                Arguments.of("12", Integer.valueOf(7)),
                Arguments.of("2=", Integer.valueOf(8)),
                Arguments.of("2-", Integer.valueOf(9)),
                Arguments.of("20", Integer.valueOf(10)),
                Arguments.of("1=0", Integer.valueOf(15)),
                Arguments.of("1-0", Integer.valueOf(20)),
                Arguments.of("1=11-2", Integer.valueOf(2022)),
                Arguments.of("1-0---0", Integer.valueOf(12345)),
                Arguments.of("1121-1110-1=0", Integer.valueOf(314159265)),
                
                Arguments.of("12111", Integer.valueOf(906)),
                Arguments.of("1=-0-2", Integer.valueOf(1747)),
                Arguments.of("2=0=", Integer.valueOf(198)),
                Arguments.of("21", Integer.valueOf(11)),
                Arguments.of("2=01", Integer.valueOf(201)),
                Arguments.of("111", Integer.valueOf(31)),
                Arguments.of("20012", Integer.valueOf(1257)),
                Arguments.of("112", Integer.valueOf(32)),
                Arguments.of("1=-1=", Integer.valueOf(353)),
                Arguments.of("1-12", Integer.valueOf(107)),
                Arguments.of("122", Integer.valueOf(37)),
                
                Arguments.of("11-", Integer.valueOf(29)),
                Arguments.of("11=", Integer.valueOf(28)),
                Arguments.of("102", Integer.valueOf(27)),
                Arguments.of("101", Integer.valueOf(26)),
                Arguments.of("100", Integer.valueOf(25)),
                Arguments.of("10-", Integer.valueOf(24)),
                Arguments.of("10=", Integer.valueOf(23)),
                Arguments.of("2=00", Integer.valueOf(200))
        );
    }
}
