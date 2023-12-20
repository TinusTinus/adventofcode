package nl.mvdr.adventofcode.adventofcode2023.day20;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link PulsePropagationPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class PulsePropagationPart2Test extends SolverTest<PulsePropagationPart1> {

    /** Constructor. */
    public PulsePropagationPart2Test() {
        super(PulsePropagationPart1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("?", "input-day20-2023.txt"));
    }
}
