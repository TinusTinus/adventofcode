package nl.mvdr.adventofcode.adventofcode2023.day16;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link TheFloorWillBeLavaPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class TheFloorWillBeLavaPart1Test extends SolverTest<TheFloorWillBeLavaPart1> {

    /** Constructor. */
    public TheFloorWillBeLavaPart1Test() {
        super(TheFloorWillBeLavaPart1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("46", "example-day16-2023.txt"),
                Arguments.of("7543", "input-day16-2023.txt"));
    }
}
