package nl.mvdr.adventofcode.adventofcode2016.day04;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link SecurityThroughObscurityPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class SecurityThroughObscurityPart1Test extends SolverTest<SecurityThroughObscurityPart1> {

    /** Constructor. */
    public SecurityThroughObscurityPart1Test() {
        super(SecurityThroughObscurityPart1.class);
    }

    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("1514", "example-day04-2016.txt"),
                Arguments.of("245102", "input-day04-2016.txt"));
    }
}
