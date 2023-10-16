package nl.mvdr.adventofcode.adventofcode2022.day25;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Test class for {@link SnafuDigit}.
 *
 * @author Martijn van de Rijdt
 */
public class SnafuDigitTest {
    
    /**
     * Test case for {@link SnafuDigit#add(SnafuDigit)}.
     * 
     * @param left string representation of the left-hand side
     * @param right string representation of the right-hand side
     * @param sum string representation of the expected sum
     */
    @ParameterizedTest
    @MethodSource
    void testAdd(char left, char right, String sum) { 
        var leftNumber = SnafuDigit.of(left);
        var rightNumber = SnafuDigit.of(right);
        
        var result = leftNumber.add(rightNumber);
        
        var expectedNumber = SnafuNumber.parse(sum);
        Assertions.assertEquals(expectedNumber, result);
    }
    
    /**
     * @return arguments for {@link #testAdd(String, String, String)}
     */
    @SuppressWarnings("boxing") // for readability
    private static Stream<Arguments> testAdd() {
        return Stream.of(
                Arguments.of('0', '0', "0"),
                Arguments.of('0', '1', "1"),
                Arguments.of('1', '0', "1"),
                Arguments.of('1', '1', "2"),
                Arguments.of('2', '1', "1="),
                Arguments.of('1', '2', "1="),
                Arguments.of('2', '2', "1-")
        );
    }
}
