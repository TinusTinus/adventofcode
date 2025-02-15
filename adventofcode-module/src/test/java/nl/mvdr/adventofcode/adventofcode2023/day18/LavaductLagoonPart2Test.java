package nl.mvdr.adventofcode.adventofcode2023.day18;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.solver.SolverTest;

/**
 * Unit test cases for {@link LavaductLagoonPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class LavaductLagoonPart2Test extends SolverTest<LavaductLagoonPart2> {

    /** Constructor. */
    public LavaductLagoonPart2Test() {
        super(LavaductLagoonPart2.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("952408144115", "example-day18-2023.txt"));
    }
}
