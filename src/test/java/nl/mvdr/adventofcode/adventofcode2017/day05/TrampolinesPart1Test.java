package nl.mvdr.adventofcode.adventofcode2017.day05;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link TrampolinesPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class TrampolinesPart1Test extends SolverTest<TrampolinesPart1> {

    /** Constructor. */
    public TrampolinesPart1Test() {
        super(TrampolinesPart1.class);
    }

    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("5", "example-day05-2017.txt"),
                Arguments.of("358131", "input-day05-2017.txt"));
    }
}
