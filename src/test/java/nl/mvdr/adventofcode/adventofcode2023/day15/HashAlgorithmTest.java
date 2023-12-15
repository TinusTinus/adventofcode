package nl.mvdr.adventofcode.adventofcode2023.day15;

import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test class for {@link HashAlgorithm}.
 *
 * @author Martijn van de Rijdt
 */
public class HashAlgorithmTest {
    
    /**
     * Test case.
     * 
     * @param expectedHash expected hash value
     * @param string string to be hashed
     */
    @ParameterizedTest
    @MethodSource
    public void testHash(int expectedHash, String string) {
        var hash = HashAlgorithm.hash(string);
        Assertions.assertEquals(expectedHash, hash);
    }
    
    /**
     * @return arguments for {@link SolverTest#testSolution(String, String)},
     *      based on the examples from the puzzle input
     */
    @SuppressWarnings("boxing")
    private static Stream<Arguments> testHash() {
        return Stream.of(
                Arguments.of(200, "H"),
                Arguments.of(153, "HA"),
                Arguments.of(172, "HAS"),
                Arguments.of(52, "HASH"),
                Arguments.of(30, "rn=1"),
                Arguments.of(253, "cm-"),
                Arguments.of(97, "qp=3"),
                Arguments.of(47, "cm=2"),
                Arguments.of(14, "qp-"),
                Arguments.of(180, "pc=4"),
                Arguments.of(9, "ot=9"),
                Arguments.of(197, "ab=5"),
                Arguments.of(48, "pc-"),
                Arguments.of(214, "pc=6"),
                Arguments.of(231, "ot=7"));
    }
}
