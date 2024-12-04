package nl.mvdr.adventofcode.adventofcode2018.day03;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test for {@link SlicePart1}.
 *
 * @author Martijn van de Rijdt
 */
public class SlicePart1Test extends SolverTest<SlicePart1> {
    /** Constructor. */
    public SlicePart1Test() {
        super(SlicePart1.class);
    }

    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("0", "example-day03-2018-0.txt"),
                Arguments.of("4", "example-day03-2018-1.txt"),
                Arguments.of("4", "example-day03-2018-2.txt"),
                Arguments.of("0", "example-day03-2018-3.txt"),
                Arguments.of("0", "example-day03-2018-4.txt"));
    }
}
