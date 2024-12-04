package nl.mvdr.adventofcode.adventofcode2023.day15;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link LensLibraryPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class LensLibraryPart2Test extends SolverTest<LensLibraryPart2> {

    /** Constructor. */
    public LensLibraryPart2Test() {
        super(LensLibraryPart2.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("145", "example-day15-2023.txt"));
    }
}
