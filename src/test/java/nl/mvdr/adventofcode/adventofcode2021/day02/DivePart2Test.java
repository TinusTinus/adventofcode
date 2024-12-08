package nl.mvdr.adventofcode.adventofcode2021.day02;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.solver.SolverTest;

/**
 * Unit test cases for {@link DivePart2}.
 *
 * @author Martijn van de Rijdt
 */
public class DivePart2Test extends SolverTest<DivePart2> {

    /** Constructor. */
    public DivePart2Test() {
        super(DivePart2.class);
    }

    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("900", "example-day02-2021.txt"));
    }
}
