package nl.mvdr.adventofcode.adventofcode2022.day23;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.solver.SolverTest;

/**
 * Unit test cases for {@link UnstableDiffusionPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class UnstableDiffusionPart1Test extends SolverTest<UnstableDiffusionPart1> {

    /** Constructor. */
    public UnstableDiffusionPart1Test() {
        super(UnstableDiffusionPart1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("25", "example-day23-2022-0.txt"),
                Arguments.of("110", "example-day23-2022-1.txt"));
    }
}
