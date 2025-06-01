package nl.mvdr.adventofcode.adventofcode2016.day19;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        return Stream.of(Arguments.of("3", "example-day19.txt"));
    }
    
    @ParameterizedTest
    @CsvSource( { "5, 3",
        "6, 5",
        "8, 1"
        } )
    void test(int startingElves, int expectedResult) {
        var solver = new Part1();
        
        var result = solver.solve(startingElves);
        
        assertEquals(expectedResult, result);
    }
}
