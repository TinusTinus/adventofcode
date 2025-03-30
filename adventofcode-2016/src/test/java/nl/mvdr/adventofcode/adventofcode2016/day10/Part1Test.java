package nl.mvdr.adventofcode.adventofcode2016.day10;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class Part1Test {

    @ParameterizedTest
    @CsvSource({ "5, 2, 2",
        "2, 5, 2",
        "2, 3, 1",
        "3, 2, 1",
        "3, 5, 0",
        "5, 3, 0"
        })
    void test(int firstMicrochipValue, int secondMicrochipValue, int expectedBotNumber) {
        var solver = new Part1(firstMicrochipValue, secondMicrochipValue);
        
        var result = solver.solve("example-day10.txt");
        
        Assertions.assertEquals("" + expectedBotNumber, result);
    }
}
