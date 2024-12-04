package nl.mvdr.adventofcode.adventofcode2020.day08;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link HandheldHaltingPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class HandheldHaltingPart1Test extends SolverTest<HandheldHaltingPart1> {

    /** Constructor. */
    public HandheldHaltingPart1Test() {
        super(HandheldHaltingPart1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("5", "example-day08-2020.txt"));
    }
}
