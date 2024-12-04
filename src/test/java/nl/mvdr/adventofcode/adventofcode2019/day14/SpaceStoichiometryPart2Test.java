package nl.mvdr.adventofcode.adventofcode2019.day14;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link SpaceStoichiometryPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class SpaceStoichiometryPart2Test extends SolverTest<SpaceStoichiometryPart2> {

    /** Constructor. */
    public SpaceStoichiometryPart2Test() {
        super(SpaceStoichiometryPart2.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("82892753", "example-day14-2019-2.txt"),
                Arguments.of("5586022", "example-day14-2019-3.txt"),
                Arguments.of("460664", "example-day14-2019-4.txt"));
    }
}
