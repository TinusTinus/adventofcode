package nl.mvdr.adventofcode.adventofcode2022.day05;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.solver.SolverTest;

/**
 * Unit test cases for {@link SupplyStacksPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class SupplyStacksPart2Test extends SolverTest<SupplyStacksPart2> {

    /** Constructor. */
    public SupplyStacksPart2Test() {
        super(SupplyStacksPart2.class);
    }

    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("MCD", "example-day05-2022.txt"));
    }
}
