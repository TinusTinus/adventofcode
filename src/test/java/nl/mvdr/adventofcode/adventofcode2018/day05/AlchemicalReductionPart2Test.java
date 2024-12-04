package nl.mvdr.adventofcode.adventofcode2018.day05;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test for {@link AlchemicalReductionPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class AlchemicalReductionPart2Test extends SolverTest<AlchemicalReductionPart2> {
    /** Constructor. */
    public AlchemicalReductionPart2Test() {
        super(AlchemicalReductionPart2.class);
    }

    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("4", "example-day05-2018-0.txt"));
    }
}
