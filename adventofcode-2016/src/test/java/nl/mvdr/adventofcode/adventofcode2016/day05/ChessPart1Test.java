package nl.mvdr.adventofcode.adventofcode2016.day05;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.solver.SolverTest;

/**
 * Unit test cases for {@link ChessPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class ChessPart1Test extends SolverTest<ChessPart1> {

    /** Constructor. */
    public ChessPart1Test() {
        super(ChessPart1.class);
    }

    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("18f47a30", "example-day05.txt"));
    }
}
