package nl.mvdr.adventofcode.adventofcode2022.day18;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.solver.SolverTest;

/**
 * Unit test cases for {@link BoilingBouldersPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class BoilingBouldersPart2Test extends SolverTest<BoilingBouldersPart2> {

    /** Constructor. */
    public BoilingBouldersPart2Test() {
        super(BoilingBouldersPart2.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("10", "example-day18-2022-0.txt"),
                Arguments.of("58", "example-day18-2022-1.txt"));
    }
}
