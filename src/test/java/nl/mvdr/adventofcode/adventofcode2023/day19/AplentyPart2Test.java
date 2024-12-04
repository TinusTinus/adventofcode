package nl.mvdr.adventofcode.adventofcode2023.day19;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link AplentyPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class AplentyPart2Test extends SolverTest<AplentyPart2> {

    /** Constructor. */
    public AplentyPart2Test() {
        super(AplentyPart2.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("167409079868000", "example-day19-2023.txt"));
    }
}
