package nl.mvdr.adventofcode.adventofcode2019.day19;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link TractorBeamPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class TractorBeamPart1Test extends SolverTest<TractorBeamPart1> {

    /** Constructor. */
    public TractorBeamPart1Test() {
        super(TractorBeamPart1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("186", "input-day19-2019.txt"));
    }
}
