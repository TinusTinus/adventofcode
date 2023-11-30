package nl.mvdr.adventofcode.adventofcode2019.day19;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link TractorBeamPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class TractorBeamPart2Test extends SolverTest<TractorBeamPart2> {

    /** Constructor. */
    public TractorBeamPart2Test() {
        super(TractorBeamPart2.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("9231141", "input-day19-2019.txt"));
    }
}
