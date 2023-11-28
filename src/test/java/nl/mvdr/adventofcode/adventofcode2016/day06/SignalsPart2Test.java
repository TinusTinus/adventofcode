package nl.mvdr.adventofcode.adventofcode2016.day06;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link SignalsPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class SignalsPart2Test extends SolverTest<SignalsPart2> {

    /** Constructor. */
    public SignalsPart2Test() {
        super(SignalsPart2.class);
    }

    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("advent", "example-day06-2016.txt"),
                Arguments.of("pljvorrk", "input-day06-2016.txt"));
    }
}
