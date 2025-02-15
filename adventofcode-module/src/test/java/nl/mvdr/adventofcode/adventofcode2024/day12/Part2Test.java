package nl.mvdr.adventofcode.adventofcode2024.day12;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.solver.SolverTest;

public class Part2Test extends SolverTest<Part2> {

    public Part2Test() {
        super(Part2.class);
    }

    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("80", "example-day12-2024-0.txt"),
                Arguments.of("436", "example-day12-2024-1.txt"),
                Arguments.of("236", "example-day12-2024-3.txt"),
                Arguments.of("368", "example-day12-2024-4.txt"));
    }
}
