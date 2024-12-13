package nl.mvdr.adventofcode.adventofcode2022.day07;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.solver.SolverTest;

/**
 * Unit test cases for {@link NoSpaceLeftOnDevicePart2}.
 *
 * @author Martijn van de Rijdt
 */
public class NoSpaceLeftOnDevicePart2Test extends SolverTest<NoSpaceLeftOnDevicePart2> {

    /** Constructor. */
    public NoSpaceLeftOnDevicePart2Test() {
        super(NoSpaceLeftOnDevicePart2.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("24933642", "example-day07-2022.txt"));
    }
}
