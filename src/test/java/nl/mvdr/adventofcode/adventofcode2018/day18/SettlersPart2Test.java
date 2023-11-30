package nl.mvdr.adventofcode.adventofcode2018.day18;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit tests for {@link SettlersPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class SettlersPart2Test extends SolverTest<SettlersPart2> {
    /** Constructor. */
    public SettlersPart2Test() {
        super(SettlersPart2.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("205296", "input-day18-2018.txt"));
    }
}
