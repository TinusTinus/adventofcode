package nl.mvdr.adventofcode.adventofcode2018.day05;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.solver.SolverTest;

/**
 * Unit test for {@link AlchemicalReductionPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class AlchemicalReductionPart1Test extends SolverTest<AlchemicalReductionPart1> {
    /** Constructor. */
    public AlchemicalReductionPart1Test() {
        super(AlchemicalReductionPart1.class);
    }

    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("10", "example-day05-2018-0.txt"),
                Arguments.of("10", "example-day05-2018-1.txt"),
                Arguments.of("10", "example-day05-2018-2.txt"),
                Arguments.of("10", "example-day05-2018-3.txt"));
    }
}
