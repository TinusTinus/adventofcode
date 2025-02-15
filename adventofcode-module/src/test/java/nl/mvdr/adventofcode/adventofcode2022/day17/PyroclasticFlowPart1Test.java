package nl.mvdr.adventofcode.adventofcode2022.day17;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.solver.SolverTest;

/**
 * Unit test cases for {@link PyroclasticFlowPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class PyroclasticFlowPart1Test extends SolverTest<PyroclasticFlowPart1> {

    /** Constructor. */
    public PyroclasticFlowPart1Test() {
        super(PyroclasticFlowPart1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("3068", "example-day17-2022.txt"));
    }
}
