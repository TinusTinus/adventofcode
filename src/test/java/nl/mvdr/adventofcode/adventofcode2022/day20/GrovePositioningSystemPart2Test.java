package nl.mvdr.adventofcode.adventofcode2022.day20;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link GrovePositioningSystemPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class GrovePositioningSystemPart2Test extends SolverTest<GrovePositioningSystemPart2> {

    /** Constructor. */
    public GrovePositioningSystemPart2Test() {
        super(GrovePositioningSystemPart2.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("1623178306", "example-day20-2022.txt"),
                Arguments.of("8927480683", "input-day20-2022.txt"));
    }
}
