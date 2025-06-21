package nl.mvdr.adventofcode.adventofcode2016.day24;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.solver.SolverTest;

class Part1Test extends SolverTest<Part1> {
    
    Part1Test() {
        super(Part1.class);
    }
    
    static Stream<Arguments> testSolution() {
        return Stream.of(Arguments.of("14", "example-day24.txt"));
    }
}
