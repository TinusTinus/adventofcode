package nl.mvdr.adventofcode.adventofcode2024.day04;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

public class Part2Test extends SolverTest<Part2> {

    public Part2Test() {
        super(Part2.class);
    }
    
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("9", "example-day04-2024.txt"));
    }
}
