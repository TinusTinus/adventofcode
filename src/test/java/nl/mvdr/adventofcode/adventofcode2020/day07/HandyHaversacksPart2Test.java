package nl.mvdr.adventofcode.adventofcode2020.day07;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link HandyHaversacksPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class HandyHaversacksPart2Test extends SolverTest<HandyHaversacksPart2> {

    /** Constructor. */
    public HandyHaversacksPart2Test() {
        super(HandyHaversacksPart2.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("32", "example-day07-2020-0.txt"),
                Arguments.of("126", "example-day07-2020-1.txt"));
    }
}
