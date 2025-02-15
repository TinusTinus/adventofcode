package nl.mvdr.adventofcode.adventofcode2022.day17;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.solver.SolverTest;

/**
 * Unit test cases for {@link PyroclasticFlowPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class PyroclasticFlowPart2Test extends SolverTest<PyroclasticFlowPart2> {

    /** Constructor. */
    public PyroclasticFlowPart2Test() {
        super(PyroclasticFlowPart2.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("1514285714288", "example-day17-2022.txt"));
    }
}
