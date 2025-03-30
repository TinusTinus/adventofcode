package nl.mvdr.adventofcode.adventofcode2016.day05;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.solver.SolverTest;

/**
 * Unit test cases for {@link ChessPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class ChessPart2Test extends SolverTest<ChessPart2> {

    /** Constructor. */
    public ChessPart2Test() {
        super(ChessPart2.class);
    }

    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("05ace8e3", "example-day05.txt"));
    }
}
