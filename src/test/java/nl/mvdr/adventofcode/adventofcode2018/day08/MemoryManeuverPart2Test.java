package nl.mvdr.adventofcode.adventofcode2018.day08;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test for {@link MemoryManeuverPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class MemoryManeuverPart2Test extends SolverTest<MemoryManeuverPart2> {
    /** Constructor. */
    public MemoryManeuverPart2Test() {
        super(MemoryManeuverPart2.class);
    }

    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("66", "example-day08-2018.txt"),
                Arguments.of("37067", "input-day08-2018.txt"));
    }
}
