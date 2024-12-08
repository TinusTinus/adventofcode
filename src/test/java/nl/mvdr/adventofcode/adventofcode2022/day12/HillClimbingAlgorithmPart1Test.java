package nl.mvdr.adventofcode.adventofcode2022.day12;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.solver.SolverTest;

/**
 * Unit test cases for {@link HillClimbingAlgorithmPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class HillClimbingAlgorithmPart1Test extends SolverTest<HillClimbingAlgorithmPart1> {

    /** Constructor. */
    public HillClimbingAlgorithmPart1Test() {
        super(HillClimbingAlgorithmPart1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("31", "example-day12-2022.txt"));
    }
}
