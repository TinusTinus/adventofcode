package nl.mvdr.adventofcode.adventofcode2024.day02;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

public class Part2Test extends SolverTest<Part2> {

    public Part2Test() {
        super(Part2.class);
    }
    
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("4", "example-day02-2024.txt"),
                Arguments.of("531", "input-day02-2024.txt"));
    }
}
