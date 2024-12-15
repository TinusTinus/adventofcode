package nl.mvdr.adventofcode.adventofcode2024.day15;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.solver.SolverTest;

public class Part1Test extends SolverTest<Part1> {

    public Part1Test() {
        super(Part1.class);
    }
    
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("10092", "example-day15-2024-0.txt"),
                Arguments.of("2028", "example-day15-2024-1.txt"));
    }
}
