package nl.mvdr.adventofcode.adventofcode2016.day04;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link SecurityThroughObscurityPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class SecurityThroughObscurityPart2Test extends SolverTest<SecurityThroughObscurityPart2> {

    /** Constructor. */
    public SecurityThroughObscurityPart2Test() {
        super(SecurityThroughObscurityPart2.class);
    }

    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("324", "input-day04-2016.txt"));
    }
}
