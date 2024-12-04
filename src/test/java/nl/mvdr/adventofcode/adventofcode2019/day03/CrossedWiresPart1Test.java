package nl.mvdr.adventofcode.adventofcode2019.day03;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link CrossedWiresPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class CrossedWiresPart1Test extends SolverTest<CrossedWiresPart1> {

    /** Constructor. */
    public CrossedWiresPart1Test() {
        super(CrossedWiresPart1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("6", "example-day03-2019-0.txt"),
                Arguments.of("159", "example-day03-2019-1.txt"),
                Arguments.of("135", "example-day03-2019-2.txt"));
    }
}
