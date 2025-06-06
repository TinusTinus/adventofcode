package nl.mvdr.adventofcode.adventofcode2016.day18;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class LikeARogueSolverTest {
    
    @ParameterizedTest
    @CsvSource( {
        "example-day18-0.txt, 1, 3",
        "example-day18-0.txt, 2, 4",
        "example-day18-0.txt, 3, 6",
        "example-day18-1.txt, 1, 3",
        "example-day18-1.txt, 2, 8",
        "example-day18-1.txt, 10, 38",
    })
    void test(String inputFile, int rows, String expectedResult) {
        var solver = new LikeARogueSolver(rows);
        
        var result = solver.solve(inputFile);
        
        assertEquals(expectedResult, result);
    }
}
