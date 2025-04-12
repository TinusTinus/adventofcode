package nl.mvdr.adventofcode.adventofcode2016.day14;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import nl.mvdr.adventofcode.solver.SolverTest;

class Part1Test extends SolverTest<Part1> {
    
    public Part1Test() {
        super(Part1.class);
    }
    
    static Stream<Arguments> testSolution() {
        return Stream.of(Arguments.of("22728", "example-day14.txt"));
    }
    
    @ParameterizedTest
    @ValueSource(ints = { 18, 39, 92, 22728 })
    void testIsTriplet(int index) {
        Character tripletCharacter = Part1.findTriplet("abc", index);
        
        assertNotNull(tripletCharacter);
    }
    
    @ParameterizedTest
    @ValueSource(ints = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9,  10, 11, 12, 13, 14, 15, 16, 17 })
    void testIsNotTriplet(int index) {
        Character tripletCharacter = Part1.findTriplet("abc", index);
        
        assertNull(tripletCharacter);
    }
    
    @ParameterizedTest
    @ValueSource(ints = { 39, 92, 22728 })
    void testIsKey(int index) {
        assertTrue(Part1.isKey("abc", index));
    }
    
    @ParameterizedTest
    @MethodSource
    void testIsNotKey(int index) {
        assertFalse(Part1.isKey("abc", index));
    }
    
    static Stream<Arguments> testIsNotKey() {
        return IntStream.range(0, 92)
                .filter(i -> i != 39)
                .mapToObj(i -> Arguments.arguments(Integer.valueOf(i)));
    }
}
