package nl.mvdr.adventofcode.adventofcode2022.day24;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link BlizzardBasinPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class BlizzardBasinPart1Test extends SolverTest<BlizzardBasinPart1> {

    /** Constructor. */
    public BlizzardBasinPart1Test() {
        super(BlizzardBasinPart1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("10", "example-day24-2022-0.txt"),
                Arguments.of("18", "example-day24-2022-1.txt"));
    }
}
