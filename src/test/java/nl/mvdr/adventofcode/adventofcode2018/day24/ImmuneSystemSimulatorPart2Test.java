package nl.mvdr.adventofcode.adventofcode2018.day24;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test for {@link ImmuneSystemSimulatorPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class ImmuneSystemSimulatorPart2Test extends SolverTest<ImmuneSystemSimulatorPart2> {
    /** Constructor. */
    public ImmuneSystemSimulatorPart2Test() {
        super(ImmuneSystemSimulatorPart2.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("8812", "input-day24-2018.txt"));
    }
}
