package nl.mvdr.adventofcode.adventofcode2018.day19;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.solver.SolverTest;

/**
 * Unit test for {@link GoWithTheFlowPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class GoWithTheFlowPart1Test extends SolverTest<GoWithTheFlowPart1> {
    /** Constructor. */
    public GoWithTheFlowPart1Test() {
        super(GoWithTheFlowPart1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("7", "example-day19-2018.txt"));
    }
}
