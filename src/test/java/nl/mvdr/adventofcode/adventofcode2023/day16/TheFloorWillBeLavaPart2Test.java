package nl.mvdr.adventofcode.adventofcode2023.day16;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link TheFloorWillBeLavaPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class TheFloorWillBeLavaPart2Test extends SolverTest<TheFloorWillBeLavaPart2> {

    /** Constructor. */
    public TheFloorWillBeLavaPart2Test() {
        super(TheFloorWillBeLavaPart2.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("51", "example-day16-2023.txt"),
                Arguments.of("8231", "input-day16-2023.txt"));
    }
}
