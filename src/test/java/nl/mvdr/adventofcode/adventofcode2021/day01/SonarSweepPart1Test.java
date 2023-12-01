package nl.mvdr.adventofcode.adventofcode2021.day01;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link SonarSweepPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class SonarSweepPart1Test extends SolverTest<SonarSweepPart1> {

    /** Constructor. */
    public SonarSweepPart1Test() {
        super(SonarSweepPart1.class);
    }

    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("7", "example-day01-2021.txt"),
                Arguments.of("1448", "input-day01-2021.txt"));
    }
}
