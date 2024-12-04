package nl.mvdr.adventofcode.adventofcode2017.day03;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link SpiralMemoryPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class SpiralMemoryPart1Test extends SolverTest<SpiralMemoryPart1> {

    /** Constructor. */
    public SpiralMemoryPart1Test() {
        super(SpiralMemoryPart1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("0", "example-day03-2017-0.txt"),
                Arguments.of("3", "example-day03-2017-1.txt"),
                Arguments.of("2", "example-day03-2017-2.txt"),
                Arguments.of("31", "example-day03-2017-3.txt"));
    }
}
