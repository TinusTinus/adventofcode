package nl.mvdr.adventofcode.adventofcode2018.day03;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test for {@link SlicePart2}.
 *
 * @author Martijn van de Rijdt
 */
public class SlicePart2Test extends SolverTest<SlicePart2> {
    /** Constructor. */
    public SlicePart2Test() {
        super(SlicePart2.class);
    }

    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("3", "example-day03-2018-1.txt"));
    }
}
