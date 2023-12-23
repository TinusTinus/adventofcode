package nl.mvdr.adventofcode.adventofcode2023.day23;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link ALongWalkPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class ALongWalkPart1Test extends SolverTest<ALongWalkPart1> {

    /** Constructor. */
    public ALongWalkPart1Test() {
        super(ALongWalkPart1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("94", "example-day23-2023.txt"),
                Arguments.of("?", "input-day23-2023.txt"));
    }
}
