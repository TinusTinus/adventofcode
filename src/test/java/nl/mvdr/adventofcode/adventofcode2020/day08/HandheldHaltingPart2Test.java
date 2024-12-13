package nl.mvdr.adventofcode.adventofcode2020.day08;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.solver.SolverTest;

/**
 * Unit test cases for {@link HandheldHaltingPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class HandheldHaltingPart2Test extends SolverTest<HandheldHaltingPart2> {

    /** Constructor. */
    public HandheldHaltingPart2Test() {
        super(HandheldHaltingPart2.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("8", "example-day08-2020.txt"));
    }
}
