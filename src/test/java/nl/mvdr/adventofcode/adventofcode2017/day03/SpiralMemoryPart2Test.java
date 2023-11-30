package nl.mvdr.adventofcode.adventofcode2017.day03;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link SpiralMemoryPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class SpiralMemoryPart2Test extends SolverTest<SpiralMemoryPart2> {

    /** Constructor. */
    public SpiralMemoryPart2Test() {
        super(SpiralMemoryPart2.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("2", "example-day03-2017-4.txt"),
                Arguments.of("4", "example-day03-2017-5.txt"),
                Arguments.of("4", "example-day03-2017-6.txt"),
                Arguments.of("5", "example-day03-2017-7.txt"),
                Arguments.of("369601", "input-day03-2017.txt"));
    }
}
