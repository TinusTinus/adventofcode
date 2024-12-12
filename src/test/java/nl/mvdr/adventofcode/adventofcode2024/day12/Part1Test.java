package nl.mvdr.adventofcode.adventofcode2024.day12;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.solver.SolverTest;

public class Part1Test extends SolverTest<Part1> {

    public Part1Test() {
        super(Part1.class);
    }
    
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("140", "example-day12-2024-0.txt"),
                Arguments.of("772", "example-day12-2024-1.txt"),
                Arguments.of("1930", "example-day12-2024-2.txt"));
    }
}
