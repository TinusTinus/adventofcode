package nl.mvdr.adventofcode.adventofcode2016.day19;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.solver.SolverTest;

class Part2Test extends SolverTest<Part2> {
    
    Part2Test() {
        super(Part2.class);
    }
    
    static Stream<Arguments> testSolution() {
        return Stream.of(Arguments.of("2", "example-day19-0.txt"),
                Arguments.of("3", "example-day19-1.txt"),
                Arguments.of("7", "example-day19-2.txt"));
    }
}
