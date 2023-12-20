package nl.mvdr.adventofcode.adventofcode2023.day20;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link PulsePropagationPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class PulsePropagationPart2Test extends SolverTest<PulsePropagationPart2> {

    /** Constructor. */
    public PulsePropagationPart2Test() {
        super(PulsePropagationPart2.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("245114020323037", "input-day20-2023.txt"));
    }
}
