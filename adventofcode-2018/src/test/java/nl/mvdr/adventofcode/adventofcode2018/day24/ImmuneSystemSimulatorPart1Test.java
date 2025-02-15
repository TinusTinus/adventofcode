package nl.mvdr.adventofcode.adventofcode2018.day24;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.solver.SolverTest;

/**
 * Unit test for {@link ImmuneSystemSimulatorPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class ImmuneSystemSimulatorPart1Test extends SolverTest<ImmuneSystemSimulatorPart1> {
    /** Constructor. */
    public ImmuneSystemSimulatorPart1Test() {
        super(ImmuneSystemSimulatorPart1.class);
    }

    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("5216", "example-day24-2018-0.txt"),
                Arguments.of("51", "example-day24-2018-1.txt"));
    }
}
