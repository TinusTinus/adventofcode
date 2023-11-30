package nl.mvdr.adventofcode.adventofcode2019.day03;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link CrossedWiresPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class CrossedWiresPart2Test extends SolverTest<CrossedWiresPart2> {

    /** Constructor. */
    public CrossedWiresPart2Test() {
        super(CrossedWiresPart2.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("30", "example-day03-2019-0.txt"),
                Arguments.of("610", "example-day03-2019-1.txt"),
                Arguments.of("410", "example-day03-2019-2.txt"),
                Arguments.of("15678", "input-day03-2019.txt"));
    }
}
