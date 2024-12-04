package nl.mvdr.adventofcode.adventofcode2021.day01;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link SonarSweepPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class SonarSweepPart2Test extends SolverTest<SonarSweepPart2> {

    /** Constructor. */
    public SonarSweepPart2Test() {
        super(SonarSweepPart2.class);
    }

    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("5", "example-day01-2021.txt"));
    }
}
