package nl.mvdr.adventofcode.adventofcode2024.day11;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PlutionianPebblesSolverTest {

    @ParameterizedTest
    @CsvSource( {
        "0,2",
        "1,3",
        "2,4",
        "3,5",
        "4,9",
        "5,13",
        "6,22",
        "25,55312" 
    })
    void testBlinks(int blinks, String expectedStones) {
        var solver = new PlutonianPebblesSolver(blinks);
        
        String result = solver.solve("example-day11-2024.txt");

        Assertions.assertEquals(expectedStones, result);
    }
}
