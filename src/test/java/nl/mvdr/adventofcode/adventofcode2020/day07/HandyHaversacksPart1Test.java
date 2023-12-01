package nl.mvdr.adventofcode.adventofcode2020.day07;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link HandyHaversacksPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class HandyHaversacksPart1Test extends SolverTest<HandyHaversacksPart1> {

    /** Constructor. */
    public HandyHaversacksPart1Test() {
        super(HandyHaversacksPart1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("4", "example-day07-2020-0.txt"),
                Arguments.of("348", "input-day07-2020.txt"));
    }
}
