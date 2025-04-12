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

class OneTimePadTest {
    
    @ParameterizedTest
    @ValueSource(ints = { 18, 39, 92, 22728 })
    void testIsTriplet(int index) {
        OneTimePad solver = new OneTimePad(1);
        
        Character tripletCharacter = solver.findTriplet("abc", index);
        
        assertNotNull(tripletCharacter);
    }
    
    @ParameterizedTest
    @ValueSource(ints = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9,  10, 11, 12, 13, 14, 15, 16, 17 })
    void testIsNotTriplet(int index) {
        OneTimePad solver = new OneTimePad(1);
        
        Character tripletCharacter = solver.findTriplet("abc", index);
        
        assertNull(tripletCharacter);
    }
    
    @ParameterizedTest
    @ValueSource(ints = { 39, 92, 22728 })
    void testIsKey(int index) {
        OneTimePad solver = new OneTimePad(1);
        
        boolean key = solver.isKey("abc", index);
        
        assertTrue(key);
    }
    
    @ParameterizedTest
    @MethodSource
    void testIsNotKey(int index) {
        OneTimePad solver = new OneTimePad(1);
        
        boolean key = solver.isKey("abc", index);
        
        assertFalse(key);
    }
    
    static Stream<Arguments> testIsNotKey() {
        return IntStream.range(0, 92)
                .filter(i -> i != 39)
                .mapToObj(i -> Arguments.arguments(Integer.valueOf(i)));
    }
    
    @ParameterizedTest
    @ValueSource(ints = { 5, 10, 22551 })
    void testIsTripletWhenKeyStretching(int index) {
        OneTimePad solver = new OneTimePad(2017);
        
        Character tripletCharacter = solver.findTriplet("abc", index);
        
        assertNotNull(tripletCharacter);
    }
    
    @ParameterizedTest
    @ValueSource(ints = { 0, 1, 2, 3, 4, 6, 7, 8, 9 })
    void testIsNotTripletWhenKeyStretching(int index) {
        OneTimePad solver = new OneTimePad(2017);
        
        Character tripletCharacter = solver.findTriplet("abc", index);
        
        assertNull(tripletCharacter);
    }
    
    @ParameterizedTest
    @ValueSource(ints = { 10, 22551 })
    void testIsKeyWhenKeyStretching(int index) {
        OneTimePad solver = new OneTimePad(2017);
        
        boolean key = solver.isKey("abc", index);
        
        assertTrue(key);
    }
}
