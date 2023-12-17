package nl.mvdr.adventofcode.adventofcode2023.day17;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link ClumsyCruciblePart2}.
 *
 * @author Martijn van de Rijdt
 */
public class ClumsyCruciblePart2Test extends SolverTest<ClumsyCruciblePart2> {

    /** Constructor. */
    public ClumsyCruciblePart2Test() {
        super(ClumsyCruciblePart2.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("94", "example-day17-2023-0.txt"),
                Arguments.of("71", "example-day17-2023-1.txt"),
                Arguments.of("881", "input-day17-2023.txt"));
    }
}
