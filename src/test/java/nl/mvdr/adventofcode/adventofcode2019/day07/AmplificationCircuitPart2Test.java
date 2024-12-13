package nl.mvdr.adventofcode.adventofcode2019.day07;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.solver.SolverTest;

/**
 * Unit test cases for {@link AmplificationCircuitPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class AmplificationCircuitPart2Test extends SolverTest<AmplificationCircuitPart2> {

    /** Constructor. */
    public AmplificationCircuitPart2Test() {
        super(AmplificationCircuitPart2.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("139629729", "example-day07-2019-3.txt"),
                Arguments.of("18216", "example-day07-2019-4.txt"));
    }
}
