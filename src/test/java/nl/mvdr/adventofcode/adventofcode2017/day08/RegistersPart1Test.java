package nl.mvdr.adventofcode.adventofcode2017.day08;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link RegistersPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class RegistersPart1Test extends SolverTest<RegistersPart1> {

    /** Constructor. */
    public RegistersPart1Test() {
        super(RegistersPart1.class);
    }

    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("1", "example-day08-2017.txt"),
                Arguments.of("4647", "input-day08-2017.txt"));
    }
}
