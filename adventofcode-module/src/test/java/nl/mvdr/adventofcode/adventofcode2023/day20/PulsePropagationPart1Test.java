package nl.mvdr.adventofcode.adventofcode2023.day20;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.solver.SolverTest;

/**
 * Unit test cases for {@link PulsePropagationPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class PulsePropagationPart1Test extends SolverTest<PulsePropagationPart1> {

    /** Constructor. */
    public PulsePropagationPart1Test() {
        super(PulsePropagationPart1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("32000000", "example-day20-2023-0.txt"),
                Arguments.of("11687500", "example-day20-2023-1.txt"));
    }
}
