package nl.mvdr.adventofcode.adventofcode2017.day21;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link FractalArtPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class FractalArtPart1Test extends SolverTest<FractalArtPart1> {

    /** Constructor. */
    public FractalArtPart1Test() {
        super(FractalArtPart1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("155", "input-day21-2017.txt"));
    }
}
