package nl.mvdr.adventofcode.adventofcode2024.day20;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;

import nl.mvdr.adventofcode.solver.SolverTest;

public class Part1Test extends SolverTest<Part1> {

    public Part1Test() {
        super(Part1.class);
    }
    
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("0", "example-day20-2024.txt"));
    }
    
    @ParameterizedTest
    @CsvSource({
        "1, 44",
        "2, 44",
        "4, 30",
        "6, 16",
        "8, 14",
        "10, 10",
        "12, 8",
        "20, 5",
        "36, 4",
        "38, 3",
        "40, 2",
        "50, 1",
        "64, 1",
        "100, 0"
    })
    void test(int threshold, int expectedResult) {
        assertSolution(new Part1(threshold), "" + expectedResult, "example-day20-2024.txt");
    }
}
