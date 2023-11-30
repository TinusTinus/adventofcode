package nl.mvdr.adventofcode.adventofcode2018.day12;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test for {@link SubterraneanSustainability}.
 *
 * @author Martijn van de Rijdt
 */
public class SubterraneanSustainabilityTest extends SolverTest<SubterraneanSustainability> {
    /** Constructor. */
    public SubterraneanSustainabilityTest() {
        super(SubterraneanSustainability.class);
    }

    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("325", "example-day12-2018.txt"),
                Arguments.of("2166", "input-day12-2018.txt"));
    }
}
