package nl.mvdr.adventofcode.adventofcode2024.day20;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;

import nl.mvdr.adventofcode.solver.SolverTest;

public class Part2Test extends SolverTest<Part2> {

    public Part2Test() {
        super(Part2.class);
    }
    
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("0", "example-day20-2024.txt"));
    }
    
    @ParameterizedTest
    @CsvSource({
        "50, 303",
        "52, 253",
        "54, 222",
        "56, 193",
        "58, 154",
        "60, 129",
        "62, 106",
        "64, 86",
        "66, 67",
        "68, 55",
        "70, 41",
        "72, 29",
        "74, 7",
        "75, 3",
        "76, 3",
        "100, 0"
    })
    void test(int threshold, int expectedResult) {
        assertSolution(new Part2(threshold), "" + expectedResult, "example-day20-2024.txt");
    }
}
