package nl.mvdr.adventofcode.adventofcode2023.day18;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link LavaductLagoonPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class LavaductLagoonPart1Test extends SolverTest<LavaductLagoonPart1> {

    /** Constructor. */
    public LavaductLagoonPart1Test() {
        super(LavaductLagoonPart1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("62", "example-day18-2023.txt"));
    }
}
