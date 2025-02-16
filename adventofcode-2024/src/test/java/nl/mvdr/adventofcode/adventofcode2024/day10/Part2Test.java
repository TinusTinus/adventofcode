package nl.mvdr.adventofcode.adventofcode2024.day10;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.solver.SolverTest;

public class Part2Test extends SolverTest<Part2> {

    public Part2Test() {
        super(Part2.class);
    }
    
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("3", "example-day10-2024-5.txt"),
                Arguments.of("13", "example-day10-2024-6.txt"),
                Arguments.of("227", "example-day10-2024-7.txt"),
                Arguments.of("81", "example-day10-2024-4.txt"));
    }
}
