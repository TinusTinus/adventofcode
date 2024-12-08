package nl.mvdr.adventofcode.adventofcode2019.day01;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.solver.SolverTest;

/**
 * Unit test cases for {@link RocketEquationPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class RocketEquationPart2Test extends SolverTest<RocketEquationPart2> {

    /** Constructor. */
    public RocketEquationPart2Test() {
        super(RocketEquationPart2.class);
    }

    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("2", "example-day01-2019-0.txt"),
                Arguments.of("2", "example-day01-2019-1.txt"),
                Arguments.of("966", "example-day01-2019-2.txt"),
                Arguments.of("50346", "example-day01-2019-3.txt"));
    }
}
