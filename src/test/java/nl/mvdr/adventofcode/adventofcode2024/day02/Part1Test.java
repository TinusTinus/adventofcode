package nl.mvdr.adventofcode.adventofcode2024.day02;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

public class Part1Test extends SolverTest<Part1> {

    public Part1Test() {
        super(Part1.class);
    }
    
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("2", "example-day02-2024.txt"),
                Arguments.of("479", "input-day02-2024.txt"));
    }
}
