package nl.mvdr.adventofcode.adventofcode2024.day08;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.solver.SolverTest;

public class Part1Test extends SolverTest<Part1> {

    public Part1Test() {
        super(Part1.class);
    }
    
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("2", "example-day08-2024-0.txt"),
                Arguments.of("4", "example-day08-2024-1.txt"),
                Arguments.of("4", "example-day08-2024-2.txt"),
                Arguments.of("14", "example-day08-2024-3.txt"));
    }
}
