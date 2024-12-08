package nl.mvdr.adventofcode.adventofcode2022.day04;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.solver.SolverTest;

/**
 * Unit test cases for {@link CampCleanupPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class CampCleanupPart2Test extends SolverTest<CampCleanupPart2> {

    /** Constructor. */
    public CampCleanupPart2Test() {
        super(CampCleanupPart2.class);
    }

    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("4", "example-day04-2022.txt"));
    }
}
