package nl.mvdr.adventofcode.adventofcode2024.day24;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.solver.SolverTest;

public class Part1Test extends SolverTest<Part1> {

    public Part1Test() {
        super(Part1.class);
    }
    
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("4", "example-day24-2024-0.txt"),
                Arguments.of("2024", "example-day24-2024-1.txt"));
    }
}
