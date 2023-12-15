package nl.mvdr.adventofcode.adventofcode2023.day15;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link LensLibraryPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class LensLibraryPart1Test extends SolverTest<LensLibraryPart1> {

    /** Constructor. */
    public LensLibraryPart1Test() {
        super(LensLibraryPart1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("1320", "example-day15-2023.txt"),
                Arguments.of("513643", "input-day15-2023.txt"));
    }
}
