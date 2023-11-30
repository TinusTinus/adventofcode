package nl.mvdr.adventofcode.adventofcode2019.day14;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link SpaceStoichiometryPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class SpaceStoichiometryPart1Test extends SolverTest<SpaceStoichiometryPart1> {

    /** Constructor. */
    public SpaceStoichiometryPart1Test() {
        super(SpaceStoichiometryPart1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("31", "example-day14-2019-0.txt"),
                Arguments.of("165", "example-day14-2019-1.txt"),
                Arguments.of("13312", "example-day14-2019-2.txt"),
                Arguments.of("180697", "example-day14-2019-3.txt"),
                Arguments.of("2210736", "example-day14-2019-4.txt"),
                Arguments.of("337075", "input-day14-2019.txt"));
    }
}
