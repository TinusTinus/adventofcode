package nl.mvdr.adventofcode.adventofcode2024.day13;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.solver.SolverTest;

public class Part1Test extends SolverTest<Part1> {

    public Part1Test() {
        super(Part1.class);
    }
    
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("280", "example-day13-2024-0.txt"),
                Arguments.of("0", "example-day13-2024-1.txt"),
                Arguments.of("200", "example-day13-2024-2.txt"),
                Arguments.of("0", "example-day13-2024-3.txt"),
                Arguments.of("480", "example-day13-2024-4.txt"));
    }
}
