package nl.mvdr.adventofcode.adventofcode2022.day05;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.solver.SolverTest;

/**
 * Unit test cases for {@link SupplyStacksPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class SupplyStacksPart1Test extends SolverTest<SupplyStacksPart1> {

    /** Constructor. */
    public SupplyStacksPart1Test() {
        super(SupplyStacksPart1.class);
    }

    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("CMZ", "example-day05-2022.txt"));
    }
}
