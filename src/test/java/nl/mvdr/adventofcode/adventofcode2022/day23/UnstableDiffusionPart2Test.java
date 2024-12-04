package nl.mvdr.adventofcode.adventofcode2022.day23;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link UnstableDiffusionPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class UnstableDiffusionPart2Test extends SolverTest<UnstableDiffusionPart2> {

    /** Constructor. */
    public UnstableDiffusionPart2Test() {
        super(UnstableDiffusionPart2.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("4", "example-day23-2022-0.txt"),
                Arguments.of("20", "example-day23-2022-1.txt"));
    }
}
