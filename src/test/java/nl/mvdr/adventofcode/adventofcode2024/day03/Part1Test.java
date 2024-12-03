package nl.mvdr.adventofcode.adventofcode2024.day03;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

public class Part1Test extends SolverTest<Part1> {

    public Part1Test() {
        super(Part1.class);
    }
    
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("161", "example-day03-2024.txt"),
                Arguments.of("?", "input-day03-2024.txt")); // 25326102 is too low
    }
}
