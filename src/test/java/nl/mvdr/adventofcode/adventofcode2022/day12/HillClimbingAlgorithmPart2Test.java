package nl.mvdr.adventofcode.adventofcode2022.day12;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link HillClimbingAlgorithmPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class HillClimbingAlgorithmPart2Test extends SolverTest<HillClimbingAlgorithmPart2> {

    /** Constructor. */
    public HillClimbingAlgorithmPart2Test() {
        super(HillClimbingAlgorithmPart2.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("29", "example-day12-2022.txt"));
    }
}
