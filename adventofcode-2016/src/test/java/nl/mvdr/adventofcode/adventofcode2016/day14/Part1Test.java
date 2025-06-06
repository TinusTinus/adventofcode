package nl.mvdr.adventofcode.adventofcode2016.day14;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.solver.SolverTest;

class Part1Test extends SolverTest<Part1> {
    
    public Part1Test() {
        super(Part1.class);
    }
    
    static Stream<Arguments> testSolution() {
        return Stream.of(Arguments.of("22728", "example-day14.txt"));
    }
}
