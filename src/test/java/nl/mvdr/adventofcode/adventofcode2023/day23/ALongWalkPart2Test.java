package nl.mvdr.adventofcode.adventofcode2023.day23;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link ALongWalkPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class ALongWalkPart2Test extends SolverTest<ALongWalkPart2> {

    /** Constructor. */
    public ALongWalkPart2Test() {
        super(ALongWalkPart2.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("154", "example-day23-2023.txt"));
    }
}
