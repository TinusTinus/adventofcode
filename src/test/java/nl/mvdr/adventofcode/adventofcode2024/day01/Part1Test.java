package nl.mvdr.adventofcode.adventofcode2024.day01;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

public class Part1Test extends SolverTest<Part1> {

    public Part1Test() {
        super(Part1.class);
    }
    
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("11", "example-day01-2024.txt"),
                Arguments.of("1651298", "input-day01-2024.txt"));
    }
}
