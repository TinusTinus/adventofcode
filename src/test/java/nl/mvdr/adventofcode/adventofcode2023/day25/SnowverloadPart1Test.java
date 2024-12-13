package nl.mvdr.adventofcode.adventofcode2023.day25;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.solver.SolverTest;

/**
 * Unit test cases for {@link SnowverloadPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class SnowverloadPart1Test extends SolverTest<SnowverloadPart1> {

    /** Constructor. */
    public SnowverloadPart1Test() {
        super(SnowverloadPart1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("54", "example-day25-2023.txt"));
    }
}
