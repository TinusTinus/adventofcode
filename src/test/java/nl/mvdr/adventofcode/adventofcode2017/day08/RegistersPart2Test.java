package nl.mvdr.adventofcode.adventofcode2017.day08;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link RegistersPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class RegistersPart2Test extends SolverTest<RegistersPart2> {

    /** Constructor. */
    public RegistersPart2Test() {
        super(RegistersPart2.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("10", "example-day08-2017.txt"));
    }
}
