package nl.mvdr.adventofcode.adventofcode2018.day18;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.solver.SolverTest;

/**
 * Unit tests for {@link SettlersPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class SettlersPart1Test extends SolverTest<SettlersPart1> {
    /** Constructor. */
    public SettlersPart1Test() {
        super(SettlersPart1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("1147", "example-day18-2018.txt"));
    }
}
