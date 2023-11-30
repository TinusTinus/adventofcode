package nl.mvdr.adventofcode.adventofcode2017.day21;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link FractalArtPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class FractalArtPart2Test extends SolverTest<FractalArtPart2> {

    /** Constructor. */
    public FractalArtPart2Test() {
        super(FractalArtPart2.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("2449665", "input-day21-2017.txt"));
    }
}
