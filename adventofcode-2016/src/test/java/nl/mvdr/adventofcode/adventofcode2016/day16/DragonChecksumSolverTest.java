package nl.mvdr.adventofcode.adventofcode2016.day16;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class DragonChecksumSolverTest {
    
    @ParameterizedTest
    @CsvSource( { "1, 100",
        "0, 001",
        "11111, 11111000000",
        "111100001010, 1111000010100101011110000",
        "10000, 10000011110",
        "10000011110, 10000011110010000111110"
    })
    void testDragonCurveStep(String input, String expectedDragonCurve) {
        var result = DragonChecksumSolver.dragonCurveStep(input);
        
        assertEquals(expectedDragonCurve, result);
    }
    
    @ParameterizedTest
    @CsvSource( { "3, 1, 100",
        "3, 0, 001",
        "11, 11111, 11111000000",
        "25, 111100001010, 1111000010100101011110000",
        "20, 10000, 10000011110010000111"
    })
    void testDragonCurve(int diskLength, String input, String expectedDragonCurve) {
        var solver = new DragonChecksumSolver(diskLength);
        
        var result = solver.dragonCurve(input);
        
        assertEquals(expectedDragonCurve, result);
    }
    
    @ParameterizedTest
    @CsvSource( { "110010110100, 110101",
        "110101, 100",
        "10000011110010000111, 0111110101",
        "0111110101, 01100"
    })
    void testChecksumStep(String input, String expected) {
        var result = DragonChecksumSolver.checksumStep(input);
        
        assertEquals(expected, result);
    }
    
    @ParameterizedTest
    @CsvSource( { "110010110100, 100",
        "10000011110010000111, 01100"
    })
    void testChecksum(String input, String expectedChecksum) {
        var result = DragonChecksumSolver.checksum(input);
        
        assertEquals(expectedChecksum, result);
    }
    
    @Test
    void testSolve() {
        var solver = new DragonChecksumSolver(20);
        var input = Stream.of("10000");
        
        var result = solver.solve(input);
        
        assertEquals("01100", result);
    }
}
