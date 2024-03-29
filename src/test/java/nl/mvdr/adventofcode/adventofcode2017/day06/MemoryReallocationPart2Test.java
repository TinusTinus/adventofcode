package nl.mvdr.adventofcode.adventofcode2017.day06;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link MemoryReallocationPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class MemoryReallocationPart2Test extends SolverTest<MemoryReallocationPart2> {

    /** Constructor. */
    public MemoryReallocationPart2Test() {
        super(MemoryReallocationPart2.class);
    }

    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("4", "example-day06-2017.txt"),
                Arguments.of("2793", "input-day06-2017.txt"));
    }
}
