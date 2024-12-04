package nl.mvdr.adventofcode.adventofcode2017.day05;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link TrampolinesPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class TrampolinesPart2Test extends SolverTest<TrampolinesPart2> {

    /** Constructor. */
    public TrampolinesPart2Test() {
        super(TrampolinesPart2.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("10", "example-day05-2017.txt"));
    }
}
