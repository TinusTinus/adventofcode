package nl.mvdr.adventofcode.adventofcode2024.day07;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.solver.SolverTest;

public class Part1Test extends SolverTest<Part1> {

    public Part1Test() {
        super(Part1.class);
    }
    
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("3749", "example-day07-2024.txt"));
    }
}
