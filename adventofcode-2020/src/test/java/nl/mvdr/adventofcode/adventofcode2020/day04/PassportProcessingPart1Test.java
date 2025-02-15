package nl.mvdr.adventofcode.adventofcode2020.day04;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.solver.SolverTest;

/**
 * Unit test cases for {@link PassportProcessingPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class PassportProcessingPart1Test extends SolverTest<PassportProcessingPart1> {

    /** Constructor. */
    public PassportProcessingPart1Test() {
        super(PassportProcessingPart1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("2", "example-day04-2020-0.txt"));
    }
}
