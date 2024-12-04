package nl.mvdr.adventofcode.adventofcode2019.day07;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link AmplificationCircuitPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class AmplificationCircuitPart1Test extends SolverTest<AmplificationCircuitPart1> {

    /** Constructor. */
    public AmplificationCircuitPart1Test() {
        super(AmplificationCircuitPart1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("43210", "example-day07-2019-0.txt"),
                Arguments.of("54321", "example-day07-2019-1.txt"),
                Arguments.of("65210", "example-day07-2019-2.txt"));
    }
}
