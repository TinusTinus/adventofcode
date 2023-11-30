package nl.mvdr.adventofcode.adventofcode2019.day11;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link SpacePolicePart1}.
 *
 * @author Martijn van de Rijdt
 */
public class SpacePolicePart1Test extends SolverTest<SpacePolicePart1> {

    /** Constructor. */
    public SpacePolicePart1Test() {
        super(SpacePolicePart1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("2252", "input-day11-2019.txt"));
    }
}
