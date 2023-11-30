package nl.mvdr.adventofcode.adventofcode2018.day19;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test for {@link GoWithTheFlowPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class GoWithTheFlowPart2Test extends SolverTest<GoWithTheFlowPart2> {
    /** Constructor. */
    public GoWithTheFlowPart2Test() {
        super(GoWithTheFlowPart2.class);
    }

    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("28137600", "input-day19-2018.txt"));
    }
}
