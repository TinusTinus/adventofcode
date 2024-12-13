package nl.mvdr.adventofcode.adventofcode2020.day04;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.solver.SolverTest;

/**
 * Unit test cases for {@link PassportProcessingPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class PassportProcessingPart2Test extends SolverTest<PassportProcessingPart2> {

    /** Constructor. */
    public PassportProcessingPart2Test() {
        super(PassportProcessingPart2.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("4", "example-day04-2020-1.txt"));
    }
}
