package nl.mvdr.adventofcode.adventofcode2016.day06;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link SignalsPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class SignalsPart1Test extends SolverTest<SignalsPart1> {

    /** Constructor. */
    public SignalsPart1Test() {
        super(SignalsPart1.class);
    }

    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("easter", "example-day06-2016.txt"),
                Arguments.of("zcreqgiv", "input-day06-2016.txt"));
    }
}
