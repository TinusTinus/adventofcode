package nl.mvdr.adventofcode.adventofcode2016.day11;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.solver.SolverTest;

class Part2Test extends SolverTest<Part2> {

    public Part2Test() {
        super(Part2.class);
    }
    
    static Stream<Arguments> testSolution() {
        return Stream.of(Arguments.of("?", "example-day11-2016.txt"));
    }
}
