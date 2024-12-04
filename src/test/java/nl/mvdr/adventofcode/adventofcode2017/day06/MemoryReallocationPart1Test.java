package nl.mvdr.adventofcode.adventofcode2017.day06;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link MemoryReallocationPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class MemoryReallocationPart1Test extends SolverTest<MemoryReallocationPart1> {

    /** Constructor. */
    public MemoryReallocationPart1Test() {
        super(MemoryReallocationPart1.class);
    }

    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("5", "example-day06-2017.txt"));
    }
}
