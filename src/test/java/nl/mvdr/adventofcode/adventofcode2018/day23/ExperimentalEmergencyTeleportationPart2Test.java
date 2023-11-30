package nl.mvdr.adventofcode.adventofcode2018.day23;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test for {@link ExperimentalEmergencyTeleportationPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class ExperimentalEmergencyTeleportationPart2Test extends SolverTest<ExperimentalEmergencyTeleportationPart2> {
    /** Constructor. */
    public ExperimentalEmergencyTeleportationPart2Test() {
        super(ExperimentalEmergencyTeleportationPart2.class);
    }

    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("36", "example-day23-2018-1.txt")
                // , Arguments.of("124623002", "input-day23-2018.txt") // This test case takes a long time
                ); 
    }
}
