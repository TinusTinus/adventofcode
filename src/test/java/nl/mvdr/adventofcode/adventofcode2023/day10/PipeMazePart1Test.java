package nl.mvdr.adventofcode.adventofcode2023.day10;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link PipeMazePart1}.
 *
 * @author Martijn van de Rijdt
 */
public class PipeMazePart1Test extends SolverTest<PipeMazePart1> {

    /** Constructor. */
    public PipeMazePart1Test() {
        super(PipeMazePart1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("4", "example-day10-2023-0.txt"),
                Arguments.of("4", "example-day10-2023-1.txt"),
                Arguments.of("8", "example-day10-2023-2.txt"),
                Arguments.of("8", "example-day10-2023-3.txt"),
                Arguments.of("?", "input-day10-2023.txt"));
    }
}
