package nl.mvdr.adventofcode.adventofcode2024.day06;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

public class Part1Test extends SolverTest<Part1> {

    public Part1Test() {
        super(Part1.class);
    }
    
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("41", "example-day06-2024.txt"));
    }
}