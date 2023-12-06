package nl.mvdr.adventofcode.adventofcode2023.day06;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link WaitForItPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class WaitForItPart1Test extends SolverTest<WaitForItPart1> {

    /** Constructor. */
    public WaitForItPart1Test() {
        super(WaitForItPart1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("288", "example-day06-2023.txt"),
                Arguments.of("?", "input-day06-2023.txt"));
    }
}
