package nl.mvdr.adventofcode.adventofcode2016.day19;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.solver.SolverTest;

class Part1Test extends SolverTest<Part1> {
    
    Part1Test() {
        super(Part1.class);
    }
    
    static Stream<Arguments> testSolution() {
        return Stream.of(Arguments.of("3", "example-day19-0.txt"),
                Arguments.of("5", "example-day19-1.txt"),
                Arguments.of("1", "example-day19-2.txt"));
    }
}
