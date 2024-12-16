package nl.mvdr.adventofcode.adventofcode2024.day15;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.solver.SolverTest;

public class Part2Test extends SolverTest<Part2> {

    public Part2Test() {
        super(Part2.class);
    }
    
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("9021", "example-day15-2024-0.txt"),
                Arguments.of("618", "example-day15-2024-2.txt"));
    }
}
