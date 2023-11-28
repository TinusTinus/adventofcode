package nl.mvdr.adventofcode.adventofcode2017.day01;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link InverseCaptchaPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class InverseCaptchaPart1Test extends SolverTest<InverseCaptchaPart1> {

    /** Constructor. */
    public InverseCaptchaPart1Test() {
        super(InverseCaptchaPart1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("3", "example-day01-2017-0.txt"),
                Arguments.of("4", "example-day01-2017-1.txt"),
                Arguments.of("0", "example-day01-2017-2.txt"),
                Arguments.of("9", "example-day01-2017-3.txt"),
                Arguments.of("1251", "input-day01-2017.txt"));
    }
}
