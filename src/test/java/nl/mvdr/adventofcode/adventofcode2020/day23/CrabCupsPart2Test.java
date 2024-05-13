package nl.mvdr.adventofcode.adventofcode2020.day23;

import nl.mvdr.adventofcode.FunctionSolver;

import nl.mvdr.adventofcode.SolverTest;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

/**
 * Unit test cases for {@link CrabCupsPart2Kt}.
 *
 * @author Martijn van de Rijdt
 */
public class CrabCupsPart2Test extends SolverTest<FunctionSolver<Long>> {

    /** Constructor. */
    public CrabCupsPart2Test() {
        super(new FunctionSolver<>(CrabCupsPart2Kt::solvePart2));
    }

    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("149245887792", "example-day23-2020.txt"),
                Arguments.of("186715244496", "input-day23-2020.txt"));
    }
}
