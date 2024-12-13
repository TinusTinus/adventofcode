package nl.mvdr.adventofcode.adventofcode2017.day01;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.solver.SolverTest;

/**
 * Unit test cases for {@link InverseCaptchaPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class InverseCaptchaPart2Test extends SolverTest<InverseCaptchaPart2> {

    /** Constructor. */
    public InverseCaptchaPart2Test() {
        super(InverseCaptchaPart2.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("6", "example-day01-2017-4.txt"),
                Arguments.of("0", "example-day01-2017-5.txt"),
                Arguments.of("4", "example-day01-2017-6.txt"),
                Arguments.of("12", "example-day01-2017-7.txt"),
                Arguments.of("4", "example-day01-2017-8.txt"));
    }
}
