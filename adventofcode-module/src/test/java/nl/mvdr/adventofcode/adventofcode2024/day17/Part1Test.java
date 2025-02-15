package nl.mvdr.adventofcode.adventofcode2024.day17;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.solver.SolverTest;

public class Part1Test extends SolverTest<Part1> {

    public Part1Test() {
        super(Part1.class);
    }
    
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("4,6,3,5,6,3,5,2,1,0", "example-day17-2024-0.txt"));
    }
}
