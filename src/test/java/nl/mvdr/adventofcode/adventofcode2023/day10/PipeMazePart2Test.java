package nl.mvdr.adventofcode.adventofcode2023.day10;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.solver.SolverTest;

/**
 * Unit test cases for {@link PipeMazePart2}.
 *
 * @author Martijn van de Rijdt
 */
public class PipeMazePart2Test extends SolverTest<PipeMazePart2> {

    /** Constructor. */
    public PipeMazePart2Test() {
        super(PipeMazePart2.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("1", "example-day10-2023-0.txt"),
                Arguments.of("1", "example-day10-2023-1.txt"),
                Arguments.of("1", "example-day10-2023-2.txt"),
                Arguments.of("1", "example-day10-2023-3.txt"),
                Arguments.of("4", "example-day10-2023-4.txt"),
                Arguments.of("4", "example-day10-2023-5.txt"),
                Arguments.of("8", "example-day10-2023-6.txt"),
                Arguments.of("10", "example-day10-2023-7.txt"));
    }
}
