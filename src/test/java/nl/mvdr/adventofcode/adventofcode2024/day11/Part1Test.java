package nl.mvdr.adventofcode.adventofcode2024.day11;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;

import nl.mvdr.adventofcode.solver.SolverTest;

class Part1Test extends SolverTest<Part1> {

    Part1Test() {
        super(Part1.class);
    }
    
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("55312", "example-day11-2024.txt"));
    }
    
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
    void testBlinks(int blinks, int expectedStones) {
        assertSolution(new Part1(blinks), "" + expectedStones, "example-day11-2024.txt");
    }
}
