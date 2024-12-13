package nl.mvdr.adventofcode.adventofcode2019.day16;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.solver.SolverTest;

/**
 * Unit test cases for {@link FlawedFrequencyTransmissionPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class FlawedFrequencyTransmissionPart2Test extends SolverTest<FlawedFrequencyTransmissionPart2> {

    /** Constructor. */
    public FlawedFrequencyTransmissionPart2Test() {
        super(FlawedFrequencyTransmissionPart2.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("84462026", "example-day16-2019-4.txt"),
                Arguments.of("78725270", "example-day16-2019-5.txt"),
                Arguments.of("53553731", "example-day16-2019-6.txt"));
    }
}
