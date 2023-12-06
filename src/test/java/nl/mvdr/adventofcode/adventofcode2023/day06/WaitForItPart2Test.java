package nl.mvdr.adventofcode.adventofcode2023.day06;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link WaitForItPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class WaitForItPart2Test extends SolverTest<WaitForItPart2> {

    /** Constructor. */
    public WaitForItPart2Test() {
        super(WaitForItPart2.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("71530", "example-day06-2023.txt"),
                Arguments.of("?", "input-day06-2023.txt"));
    }
}
