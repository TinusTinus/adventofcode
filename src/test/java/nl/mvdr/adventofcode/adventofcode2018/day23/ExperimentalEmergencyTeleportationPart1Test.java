package nl.mvdr.adventofcode.adventofcode2018.day23;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test for {@link ExperimentalEmergencyTeleportationPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class ExperimentalEmergencyTeleportationPart1Test extends SolverTest<ExperimentalEmergencyTeleportationPart1> {
    /** Constructor. */
    public ExperimentalEmergencyTeleportationPart1Test() {
        super(ExperimentalEmergencyTeleportationPart1.class);
    }

    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("7", "example-day23-2018-0.txt"));
    }
}
