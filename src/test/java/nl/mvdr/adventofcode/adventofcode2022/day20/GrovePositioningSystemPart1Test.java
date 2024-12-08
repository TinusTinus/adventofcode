package nl.mvdr.adventofcode.adventofcode2022.day20;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.solver.SolverTest;

/**
 * Unit test cases for {@link GrovePositioningSystemPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class GrovePositioningSystemPart1Test extends SolverTest<GrovePositioningSystemPart1> {

    /** Constructor. */
    public GrovePositioningSystemPart1Test() {
        super(GrovePositioningSystemPart1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("3", "example-day20-2022.txt"));
    }
}
