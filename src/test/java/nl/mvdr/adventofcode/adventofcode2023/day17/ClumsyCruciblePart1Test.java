package nl.mvdr.adventofcode.adventofcode2023.day17;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link ClumsyCruciblePart1}.
 *
 * @author Martijn van de Rijdt
 */
public class ClumsyCruciblePart1Test extends SolverTest<ClumsyCruciblePart1> {

    /** Constructor. */
    public ClumsyCruciblePart1Test() {
        super(ClumsyCruciblePart1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("102", "example-day17-2023-0.txt"),
                Arguments.of("755", "input-day17-2023.txt"));
    }
}
