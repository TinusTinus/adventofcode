package nl.mvdr.adventofcode.adventofcode2024.day08;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.solver.SolverTest;

public class Part2Test extends SolverTest<Part2> {

    public Part2Test() {
        super(Part2.class);
    }
    
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("34", "example-day08-2024-3.txt"),
                Arguments.of("9", "example-day08-2024-4.txt"));
    }
}
