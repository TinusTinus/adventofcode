package nl.mvdr.adventofcode.adventofcode2023.day19;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.solver.SolverTest;

/**
 * Unit test cases for {@link AplentyPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class AplentyPart1Test extends SolverTest<AplentyPart1> {

    /** Constructor. */
    public AplentyPart1Test() {
        super(AplentyPart1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("19114", "example-day19-2023.txt"));
    }
}
