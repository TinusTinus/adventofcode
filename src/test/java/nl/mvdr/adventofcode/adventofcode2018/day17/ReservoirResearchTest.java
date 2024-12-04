package nl.mvdr.adventofcode.adventofcode2018.day17;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test for {@link ReservoirResearch}.
 *
 * @author Martijn van de Rijdt
 */
public class ReservoirResearchTest extends SolverTest<ReservoirResearch> {
    /** Constructor. */
    public ReservoirResearchTest() {
        super(ReservoirResearch.class);
    }

    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("Reached by water: 57, settled water: 29", "example-day17-2018.txt"));
    }
}
