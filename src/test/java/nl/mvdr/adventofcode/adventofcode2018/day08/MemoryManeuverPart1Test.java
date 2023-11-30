package nl.mvdr.adventofcode.adventofcode2018.day08;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test for {@link MemoryManeuverPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class MemoryManeuverPart1Test extends SolverTest<MemoryManeuverPart1> {
    /** Constructor. */
    public MemoryManeuverPart1Test() {
        super(MemoryManeuverPart1.class);
    }

    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("138", "example-day08-2018.txt"),
                Arguments.of("40984", "input-day08-2018.txt"));
    }
}
