package nl.mvdr.adventofcode.adventofcode2022.day07;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link NoSpaceLeftOnDevicePart1}.
 *
 * @author Martijn van de Rijdt
 */
public class NoSpaceLeftOnDevicePart1Test extends SolverTest<NoSpaceLeftOnDevicePart1> {

    /** Constructor. */
    public NoSpaceLeftOnDevicePart1Test() {
        super(NoSpaceLeftOnDevicePart1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("95437", "example-day07-2022.txt"),
                Arguments.of("1454188", "input-day07-2022.txt"));
    }
}
